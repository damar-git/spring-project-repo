package com.damar.spring.utils;

import com.damar.spring.model.response.ServiceOutcome;
import org.springframework.http.HttpStatus;

public class OutcomeUtils {

    private OutcomeUtils() {

    }

    public static ServiceOutcome buildOutcome(HttpStatus httpStatus) {
        ServiceOutcome serviceOutcome = new ServiceOutcome();
        serviceOutcome.setAdditionalInfo(httpStatus.getReasonPhrase());
        serviceOutcome.setHttpResponseCode(httpStatus.value());
        return serviceOutcome;
    }

    public static ServiceOutcome buildOutcome(String additionalInfo, HttpStatus httpStatus) {
        ServiceOutcome serviceOutcome = new ServiceOutcome();
        serviceOutcome.setAdditionalInfo(additionalInfo);
        serviceOutcome.setHttpResponseCode(httpStatus.value());
        return serviceOutcome;
    }

}
