package com.damar.aopdata.controller;


import com.damar.aopdata.api.PlayerApi;
import com.damar.aopdata.exception.PlayerNotFoundException;
import com.damar.aopdata.model.Player;
import com.damar.aopdata.model.response.PlayerServiceListResponse;
import com.damar.aopdata.model.response.PlayerServiceResponse;
import com.damar.aopdata.service.PlayerService;
import com.damar.aopdata.utils.PlayerServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.damar.aopdata.constants.ErrorConstants.NO_PLAYER_FOUND_ERROR_MSG;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PlayerController implements PlayerApi {

    private PlayerService playerService;

    public ResponseEntity<PlayerServiceResponse> getPlayerById(Long playerId) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerServiceResponse.setPlayer(playerService.getById(playerId));
        } catch (PlayerNotFoundException e) {
            log.error("[PlayerController | getPlayerById] PlayerNotFoundException: ", e);
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(playerServiceResponse);
        } catch (Exception e) {
            log.error("[PlayerController | getPlayerById] Exception: ", e);
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(playerServiceResponse);
        }
        playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return ResponseEntity.ok(playerServiceResponse);
    }

    @Override
    public ResponseEntity<PlayerServiceListResponse> getAllPlayer(Integer age, String name, String surname, Boolean isActive) {
        PlayerServiceListResponse playerServiceListResponse = new PlayerServiceListResponse();
        try {
            playerServiceListResponse.setPlayerList(playerService.getAll(age, name, surname, isActive));
        } catch (Exception e) {
            log.error("[PlayerController | getAllPlayer] Exception: ", e);
            playerServiceListResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(playerServiceListResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        playerServiceListResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceListResponse, HttpStatus.OK);
    }

    public ResponseEntity<PlayerServiceResponse> updatePlayer(Player player) {
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        try {
            playerServiceResponse.setPlayer(playerService.updatePlayer(player));
        } catch (PlayerNotFoundException e) {
            log.error("[PlayerController | updatePlayer] PlayerNotFoundException: ", e);
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("[PlayerController | updatePlayer] Exception: ", e);
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
            log.error("[PlayerController | savePlayer] Exception: ", e);
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
            log.error("[PlayerController | deletePlayer] PlayerNotFoundException: ", e);
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(NO_PLAYER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("[PlayerController | deletePlayer] Exception: ", e);
            playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(playerServiceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        playerServiceResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlayerServiceListResponse> getAllPlayerByMinMaxAge(Integer maxAge, Integer minAge, Boolean equal) {
        PlayerServiceListResponse playerServiceListResponse = new PlayerServiceListResponse();
        try {
            playerServiceListResponse.setPlayerList(playerService.getAllByMinMaxAge(minAge, maxAge, equal));
        } catch (Exception e){
            log.error("[getAllPlayerByMinMaxAge] Exception: ", e);
            playerServiceListResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(playerServiceListResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        playerServiceListResponse.setPlayerServiceOutcome(PlayerServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(playerServiceListResponse, HttpStatus.OK);
    }

}
