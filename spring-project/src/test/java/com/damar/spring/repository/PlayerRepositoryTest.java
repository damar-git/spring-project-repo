package com.damar.spring.repository;

import com.damar.spring.config.H2TestConfig;
import com.damar.spring.repository.entity.PlayerDetailEntity;
import com.damar.spring.repository.entity.PlayerEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(
        classes = {H2TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void when_save_player_entity_then_find_records() {

        // when
        PlayerEntity p1 = new PlayerEntity();
        p1.setPlayerDetail(new PlayerDetailEntity());
        playerRepository.save(p1);
        playerRepository.save(new PlayerEntity());

        PlayerEntity findEntity = playerRepository.findById(p1.getPlayerId()).orElse(null);
        List<PlayerEntity> all = playerRepository.findAll();

        //then
        Assertions.assertNotNull(findEntity);
        Assertions.assertNotNull(findEntity.getPlayerDetail());
        Assertions.assertEquals(all.size(), 2);
    }

    @Test
    void when_save_player_entity_then_check_correct_fields() {

        //given
        PlayerEntity toPersist = new PlayerEntity();
        toPersist.setName("Karl");
        toPersist.setSurname("Malone");
        toPersist.setActive(Boolean.FALSE);

        // when
        playerRepository.save(toPersist);
        PlayerEntity playerEntityById = playerRepository.findById(toPersist.getPlayerId())
                .orElse(null);

        //then
        Assertions.assertNotNull(playerEntityById);
        Assertions.assertEquals(toPersist.getName(), toPersist.getName());
        Assertions.assertEquals(toPersist.getSurname(), toPersist.getSurname());
    }

    @Test
    void when_update_entity_then_check_correct_fields() {

        //given
        PlayerEntity toSave = new PlayerEntity();
        toSave.setName("Karl");
        toSave.setSurname("Malone");
        toSave.setActive(Boolean.FALSE);

        // when
        PlayerEntity toUpdate = playerRepository.save(toSave);
        toUpdate.setName("Paskal");
        toUpdate.setSurname("Siakam");
        playerRepository.save(toUpdate);

        //then
        Assertions.assertEquals(toUpdate.getName(), "Paskal");
        Assertions.assertEquals(toUpdate.getSurname(), "Siakam");
    }

    @Test
    void when_delete_player_entity_check_correct_removal(){

        //given

        PlayerEntity toPersist1 = new PlayerEntity();
        toPersist1.setName("Karl");
        toPersist1.setSurname("Malone");

        PlayerEntity toPersist2 = new PlayerEntity();
        toPersist2.setName("Paskal");
        toPersist2.setSurname("Siakam");

        //when

        PlayerEntity toRemove = playerRepository.save(toPersist1);
        PlayerEntity persisted = playerRepository.save(toPersist2);

        playerRepository.delete(toRemove);
        List<PlayerEntity> all = playerRepository.findAll();

        //then

        Assertions.assertEquals(all.size(), 1);
        Assertions.assertFalse(all.contains(toRemove));
        Assertions.assertTrue(all.contains(persisted));

    }
}
