package com.aurora.reactive.product.services;

import com.aurora.reactive.commons.events.EventType;
import com.aurora.reactive.commons.events.ProductEvent;
import com.aurora.reactive.commons.events.PurchaseOrderEvent;
import com.aurora.reactive.product.domain.ProductStock;
import com.aurora.reactive.product.exception.ProductException;
import com.aurora.reactive.product.repositories.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final StreamBridge streamBridge;
    private final ProductStockRepository productStockRepository;

    public Mono<PurchaseOrderEvent> persistAndCalculateTotalPrice(PurchaseOrderEvent purchaseOrderEvent){
        Flux.just(purchaseOrderEvent.getProductEvents())
                .flatMapIterable(list -> list)
                .flatMap(this::calculateStock)
                .flatMap(productStockRepository::save)
                .onErrorResume(e -> {
                    streamBridge.send("productProcessor-out-1", e.getMessage());
                    return Mono.empty();
                })
                .subscribe();

        purchaseOrderEvent.getProductEvents().stream().map(this::calculatePrice);

        return Mono.just(purchaseOrderEvent);
    }

    private Mono<ProductStock> calculateStock(ProductEvent productEvent){
        log.info("Product:"+productEvent.getProductId());
        return productStockRepository.findById(productEvent.getProductId())
                .switchIfEmpty(createError(productEvent));
    }

    private Mono<ProductStock> createError(ProductEvent productEvent){
        return Mono.error(new ProductException("Product Id: "+productEvent.getProductId()+" Name: "+productEvent.getName()+" not exist"));
    }

    private ProductEvent calculatePrice(ProductEvent productEvent){
        productEvent.setTotalPrice(productEvent.getUnitPrice() * productEvent.getQuantity());
        return productEvent;
    }
}
