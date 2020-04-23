package com.damar.aopdata.utils;

import com.damar.aopdata.model.UserServiceOutcome;
import org.springframework.http.HttpStatus;

public class UserServiceUtils {

    public static UserServiceOutcome buildOutcome(HttpStatus httpStatus) {
        UserServiceOutcome userServiceOutcome = new UserServiceOutcome();
        userServiceOutcome.setAdditionalInfo(httpStatus.getReasonPhrase());
        userServiceOutcome.setHttpResponseCode(httpStatus.value());
        return userServiceOutcome;
    }

    public static UserServiceOutcome buildOutcome(String additionalInfo, HttpStatus httpStatus) {
        UserServiceOutcome userServiceOutcome = new UserServiceOutcome();
        userServiceOutcome.setAdditionalInfo(additionalInfo);
        userServiceOutcome.setHttpResponseCode(httpStatus.value());
        return userServiceOutcome;
    }

}
