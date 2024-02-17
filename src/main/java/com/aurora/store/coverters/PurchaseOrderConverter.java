package com.aurora.store.coverters;

import com.aurora.store.domain.Product;
import com.aurora.store.domain.PurchaseOrder;
import com.aurora.store.events.EventType;
import com.aurora.store.events.PurchaseEvent;
import com.aurora.store.handlers.JakartaValidatorHandler;
import com.aurora.store.model.ProductModel;
import com.aurora.store.model.PurchaseOrderModel;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class PurchaseOrderConverter {
    private final JakartaValidatorHandler jakartaValidatorHandler;
    private final ModelMapper modelMapper;

    public Mono<PurchaseEvent> validateAndConvert(List<ProductModel> productModels){
        List<Product> products = productModels.stream()
                .map(jakartaValidatorHandler::validateModel)
                .map(this::convertFrom)
                .collect(toList());

        PurchaseOrder order = new PurchaseOrder();
        order.setPurchaseOrderId(new ObjectId().toString());
        order.setPurchasedProducts(products);

        PurchaseEvent purchaseEvent = new PurchaseEvent();
        purchaseEvent.setEventType(EventType.CREATE);
        purchaseEvent.setPurchaseOrder(order);

        return Mono.just(purchaseEvent);
    }

    private Product convertFrom(ProductModel productModel){
        Product product = modelMapper.map(productModel, Product.class);
        product.setProductId(new ObjectId().toString());

        return product;
    }

    public Mono<PurchaseOrderModel> convertFrom(PurchaseOrder order){
        return Mono.just(modelMapper.map(order, PurchaseOrderModel.class));
    }
}
