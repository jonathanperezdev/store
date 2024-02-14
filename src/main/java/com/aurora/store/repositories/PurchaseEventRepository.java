package com.aurora.store.repositories;

import com.aurora.store.events.PurchaseEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseEventRepository extends ReactiveMongoRepository<PurchaseEvent,Long> {
}
