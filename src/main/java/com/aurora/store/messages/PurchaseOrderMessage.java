package com.aurora.store.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderMessage {
    private String purchaseOrderId;
    private Integer purchasedProducts;
}
