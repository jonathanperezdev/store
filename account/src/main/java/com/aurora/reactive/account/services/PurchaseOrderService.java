package com.aurora.reactive.account.services;

import com.aurora.reactive.account.repositories.PurchaseOrderRepository;
import com.aurora.reactive.commons.domain.Product;
import com.aurora.reactive.commons.domain.PurchaseOrder;
import com.aurora.reactive.commons.events.PurchaseOrderEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ModelMapper modelMapper;
    public Mono<PurchaseOrder> persistOrder(PurchaseOrderEvent orderEvent){
        PurchaseOrder order = new PurchaseOrder();
        order.setPurchaseOrderId(orderEvent.getPurchaseOrderId());
        order.setTotalProducts(orderEvent.getProductEvents().size());
        order.setPurchasedProducts(orderEvent.getProductEvents().stream().map(productEvent -> modelMapper.map(productEvent, Product.class)).collect(Collectors.toList()));
        return purchaseOrderRepository.save(order);
    }
}
