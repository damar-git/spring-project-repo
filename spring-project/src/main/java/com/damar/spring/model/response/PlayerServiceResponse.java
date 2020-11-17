package com.damar.spring.model.response;

import com.damar.spring.model.Player;
import lombok.Data;

@Data
public class PlayerServiceResponse {

    private Player player;

    private ServiceOutcome serviceOutcome;

}
