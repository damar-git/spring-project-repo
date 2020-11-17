package com.damar.spring.service;


import com.damar.spring.exception.PlayerNotFoundException;
import com.damar.spring.model.Player;
import com.damar.spring.repository.PlayerRepository;
import com.damar.spring.repository.entity.PlayerDetailEntity;
import com.damar.spring.repository.entity.PlayerEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PlayerServiceDefault implements PlayerService {

    private DozerBeanMapper mapper;

    private PlayerRepository playerRepository;

    @Override
    public Player getById(Long id) throws PlayerNotFoundException {
        PlayerEntity playerEntity = playerRepository.findById(id).orElse(null);
        if (Objects.isNull(playerEntity))
            throw new PlayerNotFoundException();

        return mapper.map(playerEntity, Player.class);
    }

    @Override
    public Player updatePlayer(Player player) throws PlayerNotFoundException {
        if (BooleanUtils.isFalse(checkIfPlayerExists(player.getPlayerId())))
            throw new PlayerNotFoundException();
        PlayerEntity toUpdate = playerRepository.save(mapper.map(player, PlayerEntity.class));
        return mapper.map(toUpdate, Player.class);
    }

    @Override
    public Player savePlayer(Player player) {
        PlayerEntity newEntity = playerRepository.save(mapper.map(player, PlayerEntity.class));
        return mapper.map(newEntity, Player.class);
    }

    @Override
    public void deletePlayer(Long id) throws PlayerNotFoundException {
        PlayerEntity toDelete = playerRepository.findById(id).orElse(null);
        if (Objects.isNull(toDelete))
            throw new PlayerNotFoundException();
        playerRepository.delete(toDelete);
    }

    /**
     * Using simple Spring Data queries doesn’t scale well, as for each new parameter,
     * the amount of queries would increase significantly.
     * <p>
     * In some cases, rather than have static one, you should use a dynamic filtering process
     * that allows you to have more control over your queries.
     * The Spring Data Example API is useful for this purpose.
     * <p>
     * Below I added an updated version of the getAll method that scales much better.
     */
    public List<Player> getAllExampleAPI(Integer age, String name, String surname, Boolean isActive) {

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setActive(isActive);
        playerEntity.setName(name);
        playerEntity.setSurname(surname);
        PlayerDetailEntity playerDetailEntity = new PlayerDetailEntity();
        playerDetailEntity.setAge(age);
        playerEntity.setPlayerDetail(playerDetailEntity);

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("name", ignoreCase())
                .withMatcher("surname", ignoreCase());

        List<PlayerEntity> playerEntityList = playerRepository.findAll(Example.of(playerEntity, matcher));

        return Optional.of(playerEntityList)
                .orElse(Collections.emptyList())
                .stream()
                .map(entity -> mapper.map(entity, Player.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getAll(Integer age, String name, String surname, Boolean isActive) {
        List<PlayerEntity> playerEntityList;

        if (Objects.nonNull(isActive)) {
            playerEntityList = findAllByActiveNotNull(age, name, surname, isActive);
        } else {
            playerEntityList = findAllByActiveNull(age, name, surname);
        }

        return Optional.of(playerEntityList)
                .orElse(Collections.emptyList())
                .stream()
                .map(entity -> mapper.map(entity, Player.class))
                .collect(Collectors.toList());
    }

    private List<PlayerEntity> findAllByActiveNotNull(Integer age, String name, String surname, Boolean isActive) {
        if (Objects.nonNull(age)) {
            return findByAgeNotNullAndActiveNotNull(isActive, age, name, surname);
        } else {
            return findByAgeNullAndActiveNotNull(isActive, name, surname);
        }
    }

    private List<PlayerEntity> findAllByActiveNull(Integer age, String name, String surname) {
        if (Objects.nonNull(age)) {
            return findByAgeNotNullAndActiveNull(age, name, surname);
        } else {
            return findByAgeNullAndActiveNull(name, surname);
        }
    }

    private List<PlayerEntity> findByAgeNotNullAndActiveNull(Integer age, String name, String surname) {
        if (StringUtils.isNotBlank(name)) {
            if (StringUtils.isNotBlank(surname))
                return playerRepository.
                        findByPlayerDetail_AgeAndNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(age, name, surname);
            else
                return playerRepository.findByPlayerDetail_AgeAndNameContainingIgnoreCase(age, name);
        } else {
            if (StringUtils.isNotBlank(surname))
                return playerRepository.findByPlayerDetail_AgeAndSurnameContainingIgnoreCase(age, surname);
            else
                return playerRepository.findByPlayerDetail_Age(age);
        }
    }

    private List<PlayerEntity> findByAgeNullAndActiveNull(String name, String surname) {
        if (StringUtils.isNotBlank(name)) {
            if (StringUtils.isNotBlank(surname))
                return playerRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(name, surname);
            else
                return playerRepository.findByNameContainingIgnoreCase(name);
        } else {
            if (StringUtils.isNotBlank(surname))
                return playerRepository.findBySurnameContainingIgnoreCase(surname);
            else
                return playerRepository.findAll();
        }
    }

    private List<PlayerEntity> findByAgeNotNullAndActiveNotNull(Boolean isActive, Integer age, String name, String surname) {
        if (StringUtils.isNotBlank(name)) {
            if (StringUtils.isNotBlank(surname))
                return playerRepository.
                        findByActiveAndPlayerDetail_AgeAndNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(isActive, age, name, surname);
            else
                return playerRepository.
                        findByActiveAndPlayerDetail_AgeAndNameContainingIgnoreCase(isActive, age, name);
        } else {
            if (StringUtils.isNotBlank(surname))
                return playerRepository.findByActiveAndPlayerDetail_AgeAndSurnameContainingIgnoreCase(isActive, age, surname);
            else
                return playerRepository.findByActiveAndPlayerDetail_Age(isActive, age);
        }
    }

    private List<PlayerEntity> findByAgeNullAndActiveNotNull(Boolean isActive, String name, String surname) {
        if (StringUtils.isNotBlank(name)) {
            if (StringUtils.isNotBlank(surname))
                return playerRepository.
                        findByActiveAndNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(isActive, name, surname);
            else
                return playerRepository.findByActiveAndNameContainingIgnoreCase(isActive, name);
        } else {
            if (StringUtils.isNotBlank(surname)) {
                return playerRepository.findByActiveAndSurnameContainingIgnoreCase(isActive, surname);
            } else {
                return findByActive(isActive);
            }
        }
    }

    private List<PlayerEntity> findByActive(Boolean isActive) {
        if (BooleanUtils.isTrue(isActive))
            return playerRepository.findByActiveTrue();
        else
            return playerRepository.findByActiveFalse();
    }

    @Override
    public List<Player> getAllByMinMaxAge(Integer minAge, Integer maxAge, Boolean equal) {
        List<PlayerEntity> playerEntityList;

        if (BooleanUtils.isTrue(equal))
            playerEntityList = playerRepository.findByPlayerDetail_AgeGreaterThanEqualAndPlayerDetail_AgeLessThanEqual(minAge, maxAge);
        else
            playerEntityList = playerRepository.findByPlayerDetail_AgeGreaterThanAndPlayerDetail_AgeLessThan(minAge, maxAge);

        return Optional.of(playerEntityList)
                .orElse(Collections.emptyList())
                .stream()
                .map(entity -> mapper.map(entity, Player.class))
                .collect(Collectors.toList());
    }

    private boolean checkIfPlayerExists(Long playerId) {
        return playerRepository.existsById(playerId);
    }

}
