package com.aurora.reactive.commons.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEvent {
    private String productId;
    private String name;
    private Integer quantity;
    private Long unitPrice;
    private Long totalPrice;
}
