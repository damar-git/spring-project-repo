package com.damar.aopdata.service;


import com.damar.aopdata.exception.PlayerNotFoundException;
import com.damar.aopdata.model.Player;
import com.damar.aopdata.repository.PlayerRepository;
import com.damar.aopdata.repository.entity.PlayerEntity;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private DozerBeanMapper mapper;

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(DozerBeanMapper mapper, PlayerRepository playerRepository) {
        this.mapper = mapper;
        this.playerRepository = playerRepository;
    }

    @Override
    public Player getById(Long id) throws PlayerNotFoundException {
        PlayerEntity playerEntity = playerRepository.findById(id).orElse(null);
        if (Objects.isNull(playerEntity))
            throw new PlayerNotFoundException();
        return mapper.map(playerEntity, Player.class);
    }

    @Override
    public List<Player> getAll(Integer age, String name, Boolean isActive) {
        List<PlayerEntity> playerEntityList;

        if (Objects.nonNull(isActive)) {
            if (Objects.nonNull(age)) {
                if (StringUtils.isNotBlank(name))
                    playerEntityList = playerRepository.findByActiveAndPlayerDetail_AgeAndNameContainingIgnoreCase(isActive, age, name);
                else
                    playerEntityList = playerRepository.findByActiveAndPlayerDetail_Age(isActive, age);
            } else {
                if (StringUtils.isNotBlank(name)) {
                    playerEntityList = playerRepository.findByActiveAndNameContainingIgnoreCase(isActive, name);
                } else {
                    if (BooleanUtils.isTrue(isActive))
                        playerEntityList = playerRepository.findByActiveTrue();
                    else
                        playerEntityList = playerRepository.findByActiveFalse();
                }
            }
        } else {
            if (Objects.nonNull(age)) {
                if (StringUtils.isNotBlank(name))
                    playerEntityList = playerRepository.findByPlayerDetail_AgeAndNameContainingIgnoreCase(age, name);
                else
                    playerEntityList = playerRepository.findByPlayerDetail_Age(age);
            } else {
                if (StringUtils.isNotBlank(name))
                    playerEntityList = playerRepository.findByNameContainingIgnoreCase(name);
                else
                    playerEntityList = playerRepository.findAll();
            }
        }

        return playerEntityList.stream().map(entity -> mapper.map(entity, Player.class))
                .collect(Collectors.toList());
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

    @Override
    public List<Player> getAllByMinMaxAge(Integer minAge, Integer maxAge, Boolean equal) {
        List<PlayerEntity> playerEntityList;

        if (BooleanUtils.isTrue(equal))
            playerEntityList = playerRepository.findByPlayerDetail_AgeGreaterThanEqualAndPlayerDetail_AgeLessThanEqual(minAge, maxAge);
        else
            playerEntityList = playerRepository.findByPlayerDetail_AgeGreaterThanAndPlayerDetail_AgeLessThan(minAge, maxAge);

        return playerEntityList.stream().map(entity -> mapper.map(entity, Player.class))
                .collect(Collectors.toList());
    }

    private boolean checkIfPlayerExists(Long playerId) {
        return playerRepository.existsById(playerId);
    }

}
