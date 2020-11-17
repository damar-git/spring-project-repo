package com.damar.aopdata.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.damar.aopdata.repository")
@EntityScan(basePackages = "com.damar.aopdata.repository.entity")
@PropertySource("application-test.properties")
public class H2TestConfig {

}
