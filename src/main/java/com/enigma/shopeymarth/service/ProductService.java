package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.request.ProductRequest;
import com.enigma.shopeymarth.dto.response.ProductResponse;
import com.enigma.shopeymarth.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);
    ProductResponse getById(String id);
    List<ProductResponse> getAll();
    ProductResponse update(ProductRequest productRequest);
    void delete(String id);

//    Product create (Product product);
//    Product getById(String id);
//    List<Product> getAll();
//    Product update(Product product);


    ProductResponse createProductAndProductPrice(ProductRequest productRequest);

    Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size);
}
