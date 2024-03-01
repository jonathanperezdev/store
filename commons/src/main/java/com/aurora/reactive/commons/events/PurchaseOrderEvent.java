package com.aurora.reactive.commons.events;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PurchaseOrderEvent {
    private String purchaseOrderId;
    private List<ProductEvent> productEvents;
    private EventType eventType;
}
