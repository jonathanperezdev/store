package com.aurora.reactive.order.router;

import com.aurora.reactive.order.filters.ExceptionToHttpCodeMapperFilter;
import com.aurora.reactive.order.handlers.JakartaValidatorHandler;
import com.aurora.reactive.order.handlers.PurchaseHandlerFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderRouterFunction {

    @Bean
    public RouterFunction<ServerResponse> routeHelloWorld(PurchaseHandlerFunction handler, JakartaValidatorHandler validator) {
        return route()
                .nest(path("/order"),builder -> builder
                        .POST("/purchase", handler::createPurchaseOrder)
                        //If any Jakarta Validation is broken, this filter catch BadRequestException and returns a ServerResponse
                        .filter(new ExceptionToHttpCodeMapperFilter())
                )
                .build();
    }
}
