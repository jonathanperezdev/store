package com.aurora.reactive.order.handlers;

import com.aurora.reactive.order.coverters.PurchaseOrderConverter;
import com.aurora.reactive.order.events.PurchaseEvent;
import com.aurora.reactive.order.model.ProductModel;
import com.aurora.reactive.order.model.PurchaseOrderModel;
import com.aurora.reactive.order.producers.PurchaseOrderProducer;
import com.aurora.reactive.order.repositories.PurchaseEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Component
@RequiredArgsConstructor
public class PurchaseHandlerFunction {

    private final PurchaseEventRepository purchaseEventRepository;
    private final PurchaseOrderConverter purchaseOrderConverter;
    private final PurchaseOrderProducer purchaseOrderProducer;

    public Mono<ServerResponse> createPurchaseOrder(ServerRequest request) {
        return request.bodyToMono(new ParameterizedTypeReference<List<ProductModel>>() {})
                .flatMap(purchaseOrderConverter::validateAndConvert)
                .flatMap(purchaseEventRepository::save)
                .flatMap(this::sendPurchaseOrderMessage)
                .flatMap(event -> purchaseOrderConverter.convertFrom(event.getPurchaseOrder()))
                .flatMap(model -> created(buildCreatedURI(model.getPurchaseOrderId().toString(), request))
                        .body(Mono.just(model), PurchaseOrderModel.class));
    }
    private URI buildCreatedURI(String id, ServerRequest request){
        return request.uriBuilder().pathSegment(id).build();
    }

    private Mono<PurchaseEvent> sendPurchaseOrderMessage(PurchaseEvent purchaseEvent){
        purchaseOrderProducer.sendPurchaseOrderMessage(purchaseEvent.getPurchaseOrder());
        return Mono.just(purchaseEvent);
    }

}
