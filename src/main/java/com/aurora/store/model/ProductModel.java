package com.aurora.store.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {
    private String productId;

    @NotEmpty
    private String name;

    @Min(value = 1)
    private Integer quantity;

    @Min(value = 100)
    private Long unitPrice;
}
