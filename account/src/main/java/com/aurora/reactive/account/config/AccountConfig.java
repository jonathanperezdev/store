package com.aurora.reactive.account.config;

import com.aurora.reactive.commons.config.ModelMapperConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ModelMapperConfig.class})
public class AccountConfig {
}
