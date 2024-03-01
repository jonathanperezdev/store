package com.aurora.reactive.commons.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class PurchaseOrder {
    @Id
    private String purchaseOrderId;
    private Long totalPrice;
    private Integer totalProducts;
    private List<Product> purchasedProducts;
}
