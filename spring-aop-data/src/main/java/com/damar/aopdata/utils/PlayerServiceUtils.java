package com.damar.aopdata.utils;

import com.damar.aopdata.model.response.PlayerServiceOutcome;
import org.springframework.http.HttpStatus;

public class PlayerServiceUtils {

    public static PlayerServiceOutcome buildOutcome(HttpStatus httpStatus) {
        PlayerServiceOutcome playerServiceOutcome = new PlayerServiceOutcome();
        playerServiceOutcome.setAdditionalInfo(httpStatus.getReasonPhrase());
        playerServiceOutcome.setHttpResponseCode(httpStatus.value());
        return playerServiceOutcome;
    }

    public static PlayerServiceOutcome buildOutcome(String additionalInfo, HttpStatus httpStatus) {
        PlayerServiceOutcome playerServiceOutcome = new PlayerServiceOutcome();
        playerServiceOutcome.setAdditionalInfo(additionalInfo);
        playerServiceOutcome.setHttpResponseCode(httpStatus.value());
        return playerServiceOutcome;
    }

}
