package com.damar.aopdata.model.response;

import lombok.Data;

@Data
public class UserServiceOutcome {

    private String additionalInfo;

    private int httpResponseCode;
}
