package com.aurora.store.config;

import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class StoreConfig {
    @Bean
    @Primary
    public Validator springValidator() {
        return new LocalValidatorFactoryBean();
    }
}
