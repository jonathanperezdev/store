package com.aurora.store.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class Purchase {
    @Id
    private String purchaseId;
    private List<Product> purchasedProducts;
    private Integer numberProductsPurchased;
}
