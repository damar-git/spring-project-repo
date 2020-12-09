package com.damar.spring.configuration;

import com.damar.spring.model.Player;
import com.damar.spring.model.PlayerDetail;
import com.damar.spring.repository.entity.PlayerEntity;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
public class DozerMapperTest {

    @Autowired
    private DozerBeanMapper mapper;

    @Test
    void when_given_filled_Player_map_PlayerEntity(){

        //given

        PlayerDetail playerDetailSource = new PlayerDetail();
        playerDetailSource.setAge(50);
        playerDetailSource.setTeam("Denver Nuggets");

        Player playerSource = new Player();
        playerSource.setPlayerId(1L);
        playerSource.setName("Karl");
        playerSource.setSurname("Malone");
        playerSource.setActive(Boolean.FALSE);
        playerSource.setPlayerDetail(playerDetailSource);

        //when

        PlayerEntity playerEntityTarget = mapper.map(playerSource, PlayerEntity.class);
        Player playerTarget = mapper.map(playerEntityTarget, Player.class);

        //then

        Assertions.assertEquals(playerSource, playerTarget);
    }
}
