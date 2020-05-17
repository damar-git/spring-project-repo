package com.damar.aopdata.controller;


import com.damar.aopdata.exception.PlayerNotFoundException;
import com.damar.aopdata.api.PlayerApi;
import com.damar.aopdata.model.Player;
import com.damar.aopdata.model.response.PlayerServiceAllResponse;
import com.damar.aopdata.model.response.PlayerServiceResponse;
import com.damar.aopdata.service.PlayerService;
import com.damar.aopdata.utils.PlayerServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.damar.aopdata.constants.ErrorConstants.NO_PLAYER_FOUND_ERROR_MSG;

@RestController
public class PlayerController implements PlayerApi {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    public ResponseEntity<PlayerServiceResponse> getPlayerById(Long playerId) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerServiceResponse.setPlayer(playerService.getById(playerId));
        } catch (PlayerNotFoundException e) {
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(playerServiceResponse);
        } catch (Exception e) {
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(playerServiceResponse);
        }
        playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return ResponseEntity.ok(playerServiceResponse);
    }

    @Override
    public ResponseEntity<PlayerServiceAllResponse> getAllPlayer(Integer age, String name, String surname, Boolean isActive) {
        PlayerServiceAllResponse playerServiceAllResponse = new PlayerServiceAllResponse();
        try {
            playerServiceAllResponse.setPlayerList(playerService.getAll(age, name, surname, isActive));
        } catch (Exception e) {
            playerServiceAllResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(playerServiceAllResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        playerServiceAllResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceAllResponse, HttpStatus.OK);
    }

    public ResponseEntity<PlayerServiceResponse> updatePlayer(Player player) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerServiceResponse.setPlayer(playerService.updatePlayer(player));
        } catch (PlayerNotFoundException e) {
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceResponse, HttpStatus.OK);
    }

    public ResponseEntity<PlayerServiceResponse> savePlayer(Player player) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerServiceResponse.setPlayer(playerService.savePlayer(player));
        } catch (Exception e) {
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceResponse, HttpStatus.OK);
    }

    public ResponseEntity<PlayerServiceResponse> deletePlayer(Long id) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerService.deletePlayer(id);
        } catch (PlayerNotFoundException e) {
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlayerServiceAllResponse> getAllPlayerByMinMaxAge(Integer maxAge, Integer minAge, Boolean equal) {
        PlayerServiceAllResponse playerServiceAllResponse = new PlayerServiceAllResponse();
        try {
            playerServiceAllResponse.setPlayerList(playerService.getAllByMinMaxAge(minAge, maxAge, equal));
        } catch (Exception e){
            playerServiceAllResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(playerServiceAllResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        playerServiceAllResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceAllResponse, HttpStatus.OK);
    }

}
