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
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
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
        PlayerEntity toSave = new PlayerEntity();
        toSave.setPlayerDetail(new PlayerDetailEntity());
        playerRepository.save(toSave);
        playerRepository.save(new PlayerEntity());

        PlayerEntity toFind = playerRepository.findById(toSave.getPlayerId()).orElse(null);
        List<PlayerEntity> all = playerRepository.findAll();

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertNotNull(toFind.getPlayerDetail());
        Assertions.assertEquals(2, all.size());
    }

    @Test
    void when_save_player_entity_then_check_equals() {

        //given
        PlayerEntity toSave = new PlayerEntity();
        toSave.setName("Karl");
        toSave.setSurname("Malone");
        toSave.setActive(Boolean.FALSE);

        // when
        playerRepository.save(toSave);
        PlayerEntity toFind = playerRepository.findById(toSave.getPlayerId())
                .orElse(null);

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(toSave, toFind);
    }

    @Test
    void when_update_entity_then_check_equals() {

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
        Assertions.assertEquals("Paskal", toUpdate.getName());
        Assertions.assertEquals("Siakam", toUpdate.getSurname());
    }

    @Test
    void when_delete_player_entity_check_correct_removal() {

        //given

        PlayerEntity toSave1 = new PlayerEntity();
        toSave1.setName("Karl");
        toSave1.setSurname("Malone");

        PlayerEntity toSave2 = new PlayerEntity();
        toSave2.setName("Paskal");
        toSave2.setSurname("Siakam");

        //when

        PlayerEntity toRemove = playerRepository.save(toSave1);
        PlayerEntity persisted = playerRepository.save(toSave2);

        playerRepository.delete(toRemove);
        List<PlayerEntity> all = playerRepository.findAll();

        //then

        Assertions.assertEquals(1, all.size());
        Assertions.assertFalse(all.contains(toRemove));
        Assertions.assertTrue(all.contains(persisted));

    }
}
