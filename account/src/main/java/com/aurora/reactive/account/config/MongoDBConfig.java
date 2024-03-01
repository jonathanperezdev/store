package com.aurora.reactive.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.aurora.reactive.account.repositories")
public class MongoDBConfig {
}
