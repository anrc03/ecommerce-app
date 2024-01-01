package com.enigma.shopeymarth.dto.response;

import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private String id;
    private String productName;
    private String description;
    private Long price;
    private Integer stock;
    private StoreResponse store;
//    private List<ProductPrice> prices;
 }
