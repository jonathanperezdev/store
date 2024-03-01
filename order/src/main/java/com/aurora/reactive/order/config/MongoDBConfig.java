package com.aurora.reactive.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.aurora.reactive.order.repositories")
public class MongoDBConfig {
}
