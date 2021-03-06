package com.damar.spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.damar.spring.repository")
@EntityScan(basePackages = "com.damar.spring.repository.entity")
public class H2TestConfig {

}
