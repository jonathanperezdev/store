package com.aurora.store.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document
public class Product {
    @Id
    private String productId;
    private String name;
    private Long quantity;
    private Long unitPrice;
}
