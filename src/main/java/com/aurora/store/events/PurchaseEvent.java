package com.aurora.store.events;

import com.aurora.store.domain.Purchase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class PurchaseEvent {
    public enum PURCHASE_EVENT_TYPE{
        CREATE
    }

    @Id
    private String purchaseEventId;

    @NotNull
    private PURCHASE_EVENT_TYPE eventType;
    private Purchase purchaseOrder;
}
