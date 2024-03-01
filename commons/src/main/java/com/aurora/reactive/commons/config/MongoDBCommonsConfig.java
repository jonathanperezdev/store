package com.aurora.reactive.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.aurora.reactive.commons.repositories")
public class MongoDBCommonsConfig {
}
