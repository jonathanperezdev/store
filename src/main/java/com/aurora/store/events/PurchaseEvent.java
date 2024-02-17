package com.aurora.store.events;

import com.aurora.store.domain.PurchaseOrder;
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
