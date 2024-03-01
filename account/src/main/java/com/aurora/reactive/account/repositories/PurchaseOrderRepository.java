package com.aurora.reactive.account.repositories;

import com.aurora.reactive.commons.domain.PurchaseOrder;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends ReactiveMongoRepository<PurchaseOrder,String> {
}
