package com.aurora.reactive.order.events;

import com.aurora.reactive.commons.domain.PurchaseOrder;
import com.aurora.reactive.commons.events.EventType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class PurchaseEvent {
    @Id
    private String purchaseEventId;
    private EventType eventType;
    private PurchaseOrder purchaseOrder;
}
