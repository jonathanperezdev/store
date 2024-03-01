package com.aurora.reactive.product.repositories;

import com.aurora.reactive.product.domain.ProductStock;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends ReactiveMongoRepository<ProductStock, String> {
}
