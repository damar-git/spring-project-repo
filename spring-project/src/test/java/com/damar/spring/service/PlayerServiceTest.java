package com.damar.spring.service;


import com.damar.spring.exception.PlayerNotFoundException;
import com.damar.spring.model.Player;
import com.damar.spring.repository.PlayerRepository;
import com.damar.spring.repository.entity.PlayerEntity;
import lombok.SneakyThrows;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceDefault service;

    @Mock
    private PlayerRepository repository;

    @Spy
    private DozerBeanMapper mapper;

    @Test
    void when_given_player_data_then_save_successfully() {

        //given
        Player toSave = new Player();
        toSave.setName("John");
        toSave.setSurname("Stockton");

        PlayerEntity entity = new PlayerEntity();
        entity.setName("John");
        entity.setSurname("Stockton");

        //behaviour
        when(repository.save(any(PlayerEntity.class))).thenReturn(entity);

        Player saved = service.savePlayer(toSave);

        //then
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).map(entity, Player.class);
        verify(mapper, times(1)).map(toSave, PlayerEntity.class);
        Assertions.assertEquals(saved, toSave);

    }

    @Test
    void when_given_no_params_find_all_players() {
        //given
        PlayerEntity p1 = new PlayerEntity();
        PlayerEntity p2 = new PlayerEntity();
        PlayerEntity p3 = new PlayerEntity();

        List<PlayerEntity> allPlayers = Arrays.asList(p1, p2, p3);

        //behaviour
        when(repository.findAll()).thenReturn(allPlayers);

        List<Player> all = service.getAll(null, null, null, null);

        //then
        verify(repository, times(1)).findAll();
        verify(mapper, times(3)).map(any(PlayerEntity.class), eq(Player.class));
        Assertions.assertNotNull(all);
        Assertions.assertEquals(3, all.size());

    }

    @Test
    @SneakyThrows
    void when_given_player_id_then_remove_successfully() {

        //given
        Player toDelete = new Player();
        toDelete.setPlayerId(1L);

        PlayerEntity entityToDelete = new PlayerEntity();

        //when
        when(repository.findById(same(toDelete.getPlayerId()))).thenReturn(Optional.of(entityToDelete));

        service.deletePlayer(toDelete.getPlayerId());

        //then
        verify(repository, times(1)).findById(toDelete.getPlayerId());
        verify(repository, times(1)).delete(entityToDelete);

    }

    @Test
    void when_update_player_thow_exception_if_player_not_found() {

        //given
        Player toUpdate = new Player();
        toUpdate.setPlayerId(1L);

        //then throw exception
        Assertions.assertThrows(PlayerNotFoundException.class, () -> service.updatePlayer(toUpdate));

    }

    @Test
    void when_delete_player_thow_exception_if_player_not_found() {

        //given
        Long playerId = 1L;

        //then throw exception
        Assertions.assertThrows(PlayerNotFoundException.class, () -> service.deletePlayer(playerId));

    }


}
