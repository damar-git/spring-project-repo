package com.damar.aopdata.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan("com.damar.aopdata.service.security")
public class JwtTestConfig {
}
