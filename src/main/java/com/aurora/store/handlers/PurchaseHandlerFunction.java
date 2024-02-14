package com.aurora.store.handlers;

import com.aurora.store.events.PurchaseEvent;
import com.aurora.store.exceptions.BadRequestException;
import com.aurora.store.repositories.PurchaseEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Component
@RequiredArgsConstructor
public class PurchaseHandlerFunction {

    private final PurchaseEventRepository purchaseEventRepository;
    private final JakartaValidatorHandler jakartaValidatorHandler;

    public Mono<ServerResponse> createPurchaseOrder(ServerRequest request) {
        Mono<PurchaseEvent> body = request.bodyToMono(PurchaseEvent.class);

        return request.bodyToMono(PurchaseEvent.class)
                //Execute Handler to do Jakarta Validation
                .flatMap(jakartaValidatorHandler::validateModel)
                .flatMap(purchaseEventRepository::save)
                .flatMap(purchase -> created(buildCreatedURI(purchase.getPurchaseEventId().toString(), request)).build());
    }
    private URI buildCreatedURI(String id, ServerRequest request){
        return request.uriBuilder().pathSegment(id).build();
    }

}
