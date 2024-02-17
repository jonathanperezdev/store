package com.aurora.store.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class PurchaseOrder {
    private String purchaseOrderId;
    private List<Product> purchasedProducts;
}
