package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.request.ProductRequest;
import com.enigma.shopeymarth.dto.response.ProductResponse;
import com.enigma.shopeymarth.dto.response.StoreResponse;
import com.enigma.shopeymarth.entity.Product;
import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.ProductRepository;
import com.enigma.shopeymarth.service.ProductPriceService;
import com.enigma.shopeymarth.service.ProductService;
import com.enigma.shopeymarth.service.StoreService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreService storeService;
    private final ProductPriceService productPriceService;

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getProductName())
                .description(productRequest.getDescription())
                .build();
        productRepository.save(product);
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .build();
    }

    @Override
    public ProductResponse getById(String id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return ProductResponse.builder()
                    .id(product.getId())
                    .productName(product.getName())
                    .description(product.getDescription())
//                    .prices(product.getProductPrices())
                    .build();
        }
        return null;
    }


    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .productName(product.getName())
                        .description(product.getDescription())
                        .price(product.getProductPrices().get(0).getPrice())
                        .stock(product.getProductPrices().get(0).getStock())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public ProductResponse update(ProductRequest productRequest) {
        if (productRequest.getProductId() != null) {
            Product product = Product.builder()
                    .id(productRequest.getProductId())
                    .name(productRequest.getProductName())
                    .description(productRequest.getDescription())
                    .build();
            productRepository.save(product);
            return getById(product.getId());
        }
        return null;
    }

    @Override
    public void delete(String id) {
        if (getById(id) != null) productRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductResponse createProductAndProductPrice(ProductRequest productRequest) {
        Store store = storeService.getById(productRequest.getStoreId());

        Product product = Product.builder()
                .name(productRequest.getProductName())
                .description(productRequest.getDescription())
                .build();

        productRepository.saveAndFlush(product);

        ProductPrice productPrice = ProductPrice.builder()
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .isActive(true)
                .product(product)
                .store(store)
                .build();

        productPriceService.create(productPrice);

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productRequest.getStock())
                .store(StoreResponse.builder()
                        .id(store.getId())
                        .storeName(store.getName())
                        .noSiup(store.getNoSiup())
                        .build())
                .build();
    }

    @Override
    public Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            Join<Product, ProductPrice> productPrices = root.join("productPrices");
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(productPrices.get("price"), maxPrice));
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(specification, pageable);

        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products.getContent()) {
            Optional<ProductPrice> productPrice = product.getProductPrices()
                    .stream()
                    .filter(ProductPrice::getIsActive).findFirst();
            if (productPrice.isEmpty()) continue;
            Store store = productPrice.get().getStore();
            productResponses.add(toProductResponse(store, product, productPrice.get()));
        }
        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
    }

    private static ProductResponse toProductResponse(Store store, Product product, ProductPrice productPrice) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .store(StoreResponse.builder()
                        .id(store.getId())
                        .storeName(store.getName())
                        .noSiup(store.getNoSiup())
                        .build())
                .build();
    }
}