package com.aurora.reactive.account.consumer;

import com.aurora.reactive.account.services.PurchaseOrderService;
import com.aurora.reactive.commons.events.PurchaseOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PurchaseEventConsumer {

    private final PurchaseOrderService purchaseOrderService;

    /**
     * Reactive Consumer is a little bit special because it has a void return type, leaving framework with no reference to subscribe to.
     * Most likely you will not need to write Consumer<Flux<?>>, and instead write it as a Function<Flux<?>, Mono<Void>>
     * invoking then operator as the last operator on your stream.
     */
    @Bean
    public Function<Flux<PurchaseOrderEvent>, Mono<Void>> purchaseConsumer() {
        return flux ->
            flux.doOnNext(purchaseOrderEvent -> log.info("OrderId: "+purchaseOrderEvent.getPurchaseOrderId()))
                    .flatMap(purchaseOrderService::persistOrder)
                    .then();
    }
}
