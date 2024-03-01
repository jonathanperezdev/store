package com.aurora.reactive.product.processor;

import com.aurora.reactive.commons.events.PurchaseOrderEvent;
import com.aurora.reactive.product.exception.ProductException;
import com.aurora.reactive.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Flow;
import java.util.function.Function;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PurchaseOrderProcessor {

    private final ProductService productService;
    private final StreamBridge streamBridge;

    /**
     * Reactive Consumer is a little bit special because it has a void return type, leaving framework with no reference to subscribe to.
     * Most likely you will not need to write Consumer<Flux<?>>, and instead write it as a Function<Flux<?>, Mono<Void>>
     * invoking then operator as the last operator on your stream.
     */
    @Bean
    public Function<Flux<PurchaseOrderEvent>, Flux<PurchaseOrderEvent>> productProcessor() {
        return flux -> flux
                .flatMap(purchaseOrderEvent -> productService.persistAndCalculateTotalPrice(purchaseOrderEvent));
    }
}
