package com.damar.spring.service;


import com.damar.spring.exception.PlayerNotFoundException;
import com.damar.spring.model.Player;

import java.util.List;

public interface PlayerService {

    Player getById(Long id) throws PlayerNotFoundException;

    List<Player> getAll(Integer age, String name, String surname, Boolean isActive);

    Player updatePlayer(Player player) throws PlayerNotFoundException;

    Player savePlayer(Player player);

    void deletePlayer(Long id) throws PlayerNotFoundException;

    List<Player> getAllByMinMaxAge(Integer minAge, Integer maxAge, Boolean equal);

}
