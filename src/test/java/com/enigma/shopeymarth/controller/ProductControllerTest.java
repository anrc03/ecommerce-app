package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.dto.request.ProductRequest;
import com.enigma.shopeymarth.dto.response.CommonResponse;
import com.enigma.shopeymarth.dto.response.ProductResponse;
import com.enigma.shopeymarth.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createProduct() {
        //data dummy
        ProductRequest productRequest = new ProductRequest();
//        productRequest.setProductName("Oreo");
//        productRequest.setPrice(100L);

        ProductResponse productResponse = new ProductResponse();
//        productResponse.setId("1");
//        productResponse.setProductName("Oreo");
//        productResponse.setPrice(100L);

        when(productService.createProductAndProductPrice(productRequest)).thenReturn(productResponse);

        ResponseEntity<?> responseEntity = productController.createProduct(productRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        CommonResponse<ProductResponse> commonResponse = (CommonResponse<ProductResponse>) responseEntity.getBody();
        assertEquals(HttpStatus.CREATED.value(), commonResponse.getStatusCode());
        assertEquals("Successfully created new product", commonResponse.getMessage());

    }

    @Test
    void getAllProduct() {
    }

    @Test
    void getAllProductPage() {
    }
}