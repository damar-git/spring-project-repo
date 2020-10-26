package com.damar.aopdata.api;

import com.damar.aopdata.configuration.SwaggerConfig;
import com.damar.aopdata.model.Player;
import com.damar.aopdata.model.response.PlayerServiceListResponse;
import com.damar.aopdata.model.response.PlayerServiceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.TAG_1})
@RequestMapping("/nba-portal-api")
public interface PlayerApi {

    @ApiOperation(value = "Retrieve a player")
    @GetMapping(value = "/player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlayerServiceResponse> getPlayerById(@ApiParam(value = "the player id", required = true)
                                                        @PathVariable(value = "id") Long playerId,
                                                        @RequestHeader(value="jwt") String jwt);

    @ApiOperation(value = "Update a player")
    @PutMapping(value = "/player", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlayerServiceResponse> updatePlayer(@ApiParam(value = "the player object", required = true)
                                                       @RequestBody Player player,
                                                       @RequestHeader(value="jwt") String jwt);

    @ApiOperation(value = "Save a player")
    @PostMapping(value = "/player", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlayerServiceResponse> savePlayer(@ApiParam(value = "the player object", required = true)
                                                     @RequestBody Player player,
                                                     @RequestHeader(value="jwt") String jwt);

    @ApiOperation(value = "Delete a player")
    @DeleteMapping(value = "/player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlayerServiceResponse> deletePlayer(@ApiParam(value = "the player id", required = true)
                                                       @PathVariable(value = "id") Long playerId,
                                                       @RequestHeader(value="jwt") String jwt);

    @ApiOperation(value = "Retrieve all players with params")
    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlayerServiceListResponse> getAllPlayer(@ApiParam(value = "the player age")
                                                           @RequestParam(value = "age", required = false) Integer age,
                                                           @ApiParam(value = "the player name")
                                                           @RequestParam(value = "name", required = false) String name,
                                                           @ApiParam(value = "the player surname")
                                                           @RequestParam(value = "surname", required = false) String surname,
                                                           @ApiParam(value = "indicates whether the player should be active or not")
                                                           @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                           @RequestHeader(value="jwt") String jwt);

    @ApiOperation(value = "Retrieve all players with min/max age params")
    @GetMapping(value = "/players/age", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlayerServiceListResponse> getAllPlayerByMinMaxAge(@ApiParam(value = "the player max age", required = true)
                                                                      @RequestParam(value = "maxAge") Integer maxAge,
                                                                      @ApiParam(value = "the player min age", required = true)
                                                                      @RequestParam(value = "minAge") Integer minAge,
                                                                      @ApiParam(value = "indicates whether the age input value should be included or not", required = true)
                                                                      @RequestParam(value = "equal") Boolean equal,
                                                                      @RequestHeader(value="jwt") String jwt);

}
