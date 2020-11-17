package com.damar.spring.mapper;

import com.damar.spring.model.Player;
import com.damar.spring.model.PlayerDetail;
import com.damar.spring.repository.entity.PlayerEntity;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DozerMapperTest {

    @Spy
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
