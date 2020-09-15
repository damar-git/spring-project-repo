package com.damar.aopdata.service;


import com.damar.aopdata.exception.PlayerNotFoundException;
import com.damar.aopdata.model.Player;

import java.util.List;

public interface PlayerService {

    Player getById(Long id);

    List<Player> getAll(Integer age, String name, String surname, Boolean isActive);

    Player updatePlayer(Player player);

    Player savePlayer(Player player);

    void deletePlayer(Long id);

    List<Player> getAllByMinMaxAge(Integer minAge, Integer maxAge, Boolean equal);

}
