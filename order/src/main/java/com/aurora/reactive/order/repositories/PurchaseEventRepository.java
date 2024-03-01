package com.aurora.reactive.order.repositories;

import com.aurora.reactive.order.events.PurchaseEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseEventRepository extends ReactiveMongoRepository<PurchaseEvent,Long> {
}
