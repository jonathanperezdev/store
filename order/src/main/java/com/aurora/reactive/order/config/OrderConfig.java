package com.aurora.reactive.order.config;

import com.aurora.reactive.commons.config.ModelMapperConfig;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@Import(ModelMapperConfig.class)
public class OrderConfig {
    @Bean
    @Primary
    public Validator springValidator() {
        return new LocalValidatorFactoryBean();
    }
}
