package com.damar.aopdata.model.response;

import com.damar.aopdata.model.Player;
import lombok.Data;

@Data
public class PlayerServiceResponse {

    private Player player;

    private PlayerServiceOutcome playerServiceOutcome;

}
