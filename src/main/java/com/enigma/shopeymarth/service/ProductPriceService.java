package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.entity.ProductPrice;
import org.springframework.stereotype.Service;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);
    ProductPrice getById(String id);
    ProductPrice findProductPriceIsActive(String productId, Boolean active);
}
