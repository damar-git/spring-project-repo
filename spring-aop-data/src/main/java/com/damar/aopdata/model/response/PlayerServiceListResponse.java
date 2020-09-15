package com.damar.aopdata.model.response;

import com.damar.aopdata.model.Player;
import lombok.Data;

import java.util.List;

@Data
public class PlayerServiceListResponse {

    private List<Player> playerList;

    private PlayerServiceOutcome playerServiceOutcome;
}
