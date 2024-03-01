package com.aurora.reactive.commons.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Product {
    private String productId;
    private String name;
    private Integer quantity;
    private Long unitPrice;
}
