package com.aurora.reactive.order.producers;

import com.aurora.reactive.commons.domain.PurchaseOrder;
import com.aurora.reactive.commons.events.EventType;
import com.aurora.reactive.commons.events.ProductEvent;
import com.aurora.reactive.commons.events.PurchaseOrderEvent;
import com.aurora.reactive.order.exceptions.MessageNotSentException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseOrderProducer {
    private final StreamBridge streamBridge;
    private final ModelMapper modelMapper;


    private static final String PURCHASE_BINDER = "purchaseProducer-out-0";
    private static final String PRODUCT_BINDER = "productProducer-out-0";

    public void sendPurchaseOrderMessage(PurchaseOrder purchaseOrder){
        PurchaseOrderEvent purchaseOrderEvent = new PurchaseOrderEvent();
        purchaseOrderEvent.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        purchaseOrderEvent.setEventType(EventType.CREATE);
        purchaseOrderEvent.setProductEvents(purchaseOrder.getPurchasedProducts()
                .stream()
                .map(product -> modelMapper.map(product, ProductEvent.class))
                .collect(Collectors.toList()));


        if(!streamBridge.send(PRODUCT_BINDER,purchaseOrderEvent)){
            throw new MessageNotSentException("Message not sent to "+PRODUCT_BINDER);
        }
    }
}
