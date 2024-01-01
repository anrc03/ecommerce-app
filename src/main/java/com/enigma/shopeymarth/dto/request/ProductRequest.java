package com.enigma.shopeymarth.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {

    private String productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product description is required")
    private String description;

    @NotBlank(message = "Product price is required")
    @Min(value = 0, message = "Price must be 0 or more")
    private Long price;

    @NotBlank(message = "Product stock is required")
    @Min(value = 0, message = "Stock must be 0 or more")
    private Integer stock;

    @NotBlank(message = "Store ID is required")
    private String storeId;
}
