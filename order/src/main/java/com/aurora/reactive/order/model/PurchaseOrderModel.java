package com.aurora.reactive.order.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseOrderModel {
    private String purchaseOrderId;
    private List<ProductModel> purchasedProducts;
}
