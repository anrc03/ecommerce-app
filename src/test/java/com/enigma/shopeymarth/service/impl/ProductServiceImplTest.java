package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.request.ProductRequest;
import com.enigma.shopeymarth.dto.request.StoreRequest;
import com.enigma.shopeymarth.dto.response.ProductResponse;
import com.enigma.shopeymarth.entity.Product;
import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.ProductPriceRepository;
import com.enigma.shopeymarth.repository.ProductRepository;
import com.enigma.shopeymarth.service.ProductPriceService;
import com.enigma.shopeymarth.service.ProductService;
import com.enigma.shopeymarth.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductPriceService productPriceService = mock(ProductPriceService.class);
    private final StoreService storeService = mock(StoreService.class);
    private final ProductService productService = new ProductServiceImpl(productRepository, storeService, productPriceService);

    @BeforeEach
    public void setUp() {
        // reset mock behaviour
        reset(productRepository, storeService, productPriceService);
    }

    @Test
    void createProductAndProductPrice() {
        // store request
        Store dummyStore = new Store();
        dummyStore.setId("store1");
        dummyStore.setName("Berkah Selalu");
        dummyStore.setNoSiup("12345");

        when(storeService.getById(anyString())).thenReturn(dummyStore);

        Product saveProduct = new Product();
        saveProduct.setId("productId");
        saveProduct.setName("Oreo");
        saveProduct.setDescription("Delicious");

        // data dummy request
        ProductRequest dummyRequest = mock(ProductRequest.class);
        when(dummyRequest.getStoreId()).thenReturn("storeId");
        when(dummyRequest.getProductName()).thenReturn(saveProduct.getName());
        when(dummyRequest.getDescription()).thenReturn(saveProduct.getDescription());
        when(dummyRequest.getPrice()).thenReturn(1000000L);
        when(dummyRequest.getStock()).thenReturn(10);

        ProductResponse productResponse = productService.createProductAndProductPrice(dummyRequest);

        assertNotNull(productResponse);
        assertEquals(saveProduct.getName(), productResponse.getProductName());

        assertEquals(dummyRequest.getPrice(), productResponse.getPrice());

        verify(storeService).getById(anyString());
        verify(productRepository).saveAndFlush(any(Product.class));
        verify(productPriceService).create(any(ProductPrice.class));
    }
}