package com.aurora.reactive.product.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class ProductStock {
    @Id
    private String productId;
    private String productName;
    private Integer stock = 0;
    private Long unitPrice;
}

