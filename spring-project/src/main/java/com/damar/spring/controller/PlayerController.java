package com.damar.spring.controller;


import com.damar.spring.api.PlayerApi;
import com.damar.spring.exception.PlayerNotFoundException;
import com.damar.spring.model.Player;
import com.damar.spring.model.response.PlayerServiceListResponse;
import com.damar.spring.model.response.PlayerServiceResponse;
import com.damar.spring.service.PlayerService;
import com.damar.spring.utils.OutcomeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.damar.spring.constants.ErrorConstants.NO_PLAYER_FOUND_ERROR_MSG;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PlayerController implements PlayerApi {

    private PlayerService playerService;

    public ResponseEntity<PlayerServiceResponse> getPlayerById(Long playerId, String jwt) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerServiceResponse.setPlayer(playerService.getById(playerId));
        } catch (PlayerNotFoundException e) {
            log.error("[PlayerController | getPlayerById] PlayerNotFoundException: ", e);
            playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(playerServiceResponse);
        }
        playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.OK));
        return ResponseEntity.ok(playerServiceResponse);
    }

    @Override
    public ResponseEntity<PlayerServiceListResponse> getAllPlayer(Integer age, String name,
                                                                  String surname, Boolean isActive, String jwt) {
        PlayerServiceListResponse playerServiceListResponse = new PlayerServiceListResponse();
        playerServiceListResponse.setPlayerList(playerService.getAll(age, name, surname, isActive));
        playerServiceListResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceListResponse, HttpStatus.OK);
    }

    public ResponseEntity<PlayerServiceResponse> updatePlayer(Player player, String jwt) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerServiceResponse.setPlayer(playerService.updatePlayer(player));
        } catch (PlayerNotFoundException e) {
            log.error("[PlayerController | updatePlayer] PlayerNotFoundException: ", e);
            playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.NOT_FOUND);
        }
        playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceResponse, HttpStatus.OK);
    }

    public ResponseEntity<PlayerServiceResponse> savePlayer(Player player, String jwt) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        playerServiceResponse.setPlayer(playerService.savePlayer(player));
        playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceResponse, HttpStatus.OK);
    }

    public ResponseEntity<PlayerServiceResponse> deletePlayer(Long id, String jwt) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerService.deletePlayer(id);
        } catch (PlayerNotFoundException e) {
            log.error("[PlayerController | deletePlayer] PlayerNotFoundException: ", e);
            playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.NOT_FOUND);
        }
        playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlayerServiceListResponse> getAllPlayerByMinMaxAge(Integer maxAge, Integer minAge,
                                                                             Boolean equal, String jwt) {
        PlayerServiceListResponse playerServiceListResponse = new PlayerServiceListResponse();
        playerServiceListResponse.setPlayerList(playerService.getAllByMinMaxAge(minAge, maxAge, equal));
        playerServiceListResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceListResponse, HttpStatus.OK);
    }

}
