package com.damar.aopdata.configuration;

import com.damar.aopdata.repository.entity.UserEntity;
import com.damar.aopdata.model.User;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    public DozerBeanMapper initializeMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(createUserMappingBuilder());
        return mapper;
    }

    private BeanMappingBuilder createUserMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(UserEntity.class, User.class);
            }
        };
    }

}
