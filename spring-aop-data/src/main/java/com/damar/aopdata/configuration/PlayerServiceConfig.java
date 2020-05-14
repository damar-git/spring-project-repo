package com.damar.aopdata.configuration;

import com.damar.aopdata.repository.entity.PlayerEntity;
import com.damar.aopdata.model.Player;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerServiceConfig {

    @Bean
    public DozerBeanMapper initializeMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(createPlayerMappingBuilder());
        return mapper;
    }

    private BeanMappingBuilder createPlayerMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(PlayerEntity.class, Player.class);
            }
        };
    }

}
