package com.aurora.store.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductMessage {
    private String purchaseOrderId;
    private String productId;
    private String name;
    private Integer quantity;
    private Long unitPrice;
}
