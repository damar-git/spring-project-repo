package com.damar.spring.model.response;

import com.damar.spring.model.Player;
import lombok.Data;

import java.util.List;

@Data
public class PlayerServiceListResponse {

    private List<Player> playerList;

    private ServiceOutcome serviceOutcome;
}
