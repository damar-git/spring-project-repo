package com.damar.aopdata.service;


import com.damar.aopdata.model.Player;
import com.damar.aopdata.model.PlayerDetail;
import com.damar.aopdata.repository.PlayerRepository;
import com.damar.aopdata.repository.entity.PlayerEntity;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceDefault service;

    @Mock
    private PlayerRepository repository;

    @Mock
    private DozerBeanMapper mapper;

    @Test
    void when_given_player_data_then_save_successfully() {

        //given

        Player playerServiceInput = new Player();
        Player playerServiceOutput = new Player();
        playerServiceOutput.setPlayerId(1L);

        PlayerEntity entity = new PlayerEntity();

        //behaviour
        when(mapper.map(same(playerServiceInput), eq(PlayerEntity.class))).thenReturn(entity);
        when(repository.save(isA(PlayerEntity.class))).thenReturn(entity);
        when(mapper.map(same(entity), eq(Player.class))).thenReturn(playerServiceOutput);

        //
        Player playerServiceResponse = service.savePlayer(playerServiceInput);

        //then
        verify(repository, times(1)).save(isA(PlayerEntity.class));
        verify(mapper, times(1)).map(same(entity), eq(Player.class));
        verify(mapper, times(1)).map(same(playerServiceInput), eq(PlayerEntity.class));
        Assertions.assertEquals(playerServiceResponse, playerServiceOutput);

    }


}
