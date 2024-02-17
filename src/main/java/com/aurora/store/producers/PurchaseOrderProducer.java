package com.aurora.store.producers;

import com.aurora.store.domain.Product;
import com.aurora.store.domain.PurchaseOrder;
import com.aurora.store.events.PurchaseEvent;
import com.aurora.store.exceptions.MessageNotSentException;
import com.aurora.store.messages.ProductMessage;
import com.aurora.store.messages.PurchaseOrderMessage;
import com.aurora.store.model.ProductModel;
import com.aurora.store.model.PurchaseOrderModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseOrderProducer {
    private final StreamBridge streamBridge;
    private final ModelMapper modelMapper;


    private static final String PURCHASE_BINDER = "purchaseProducer-out-0";
    private static final String PRODUCT_BINDER = "productProducer-out-0";

    public void sendPurchaseOrderMessages(PurchaseOrder purchaseOrder){
        PurchaseOrderMessage purchaseOrderMessage = new PurchaseOrderMessage();
        purchaseOrderMessage.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        purchaseOrderMessage.setPurchasedProducts(purchaseOrder.getPurchasedProducts().size());

        boolean sendPurchaseMessage = streamBridge.send(PURCHASE_BINDER, purchaseOrderMessage);
        if(!sendPurchaseMessage){
            throw new MessageNotSentException("Message not sent to "+PURCHASE_BINDER);
        }

        purchaseOrder.getPurchasedProducts().stream()
                .map(product -> convertFrom(product, purchaseOrder.getPurchaseOrderId()))
                .forEach(this::sendProductMessage);
    }

    private ProductMessage convertFrom(Product product, String purchaseOrderId){
        ProductMessage productMessage = modelMapper.map(product, ProductMessage.class);
        productMessage.setPurchaseOrderId(purchaseOrderId);

        return productMessage;
    }

    private void sendProductMessage(ProductMessage message){
        boolean sendProductMessage = streamBridge.send(PRODUCT_BINDER, message);
        if(!sendProductMessage){
            throw new MessageNotSentException("Message not sent to "+PRODUCT_BINDER);
        }
    }
}
