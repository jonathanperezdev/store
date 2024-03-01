package com.aurora.reactive.order.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@Slf4j
public class ErrorHandlerConsumer {
    @Bean
    public Function<Flux<String>, Mono<Void>> errorConsumer() {
        return flux ->
                flux.doOnNext(error -> log.info("Error get "+error))
                        .then();
    }
}
