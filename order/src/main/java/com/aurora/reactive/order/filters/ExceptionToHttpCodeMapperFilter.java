package com.aurora.reactive.order.filters;

import com.aurora.reactive.order.exceptions.BadRequestException;
import com.aurora.reactive.order.exceptions.MessageNotSentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;

public class ExceptionToHttpCodeMapperFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Override
    public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
        return next.handle(request)
                .onErrorResume(BadRequestException.class, e -> badRequest().bodyValue(e.getErrors()))
                .onErrorResume(MessageNotSentException.class, e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(e.getMessage()));
    }
}
