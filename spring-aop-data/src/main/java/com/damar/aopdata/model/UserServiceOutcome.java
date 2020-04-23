package com.damar.aopdata.model;

import lombok.Data;

@Data
public class UserServiceOutcome {

    private String additionalInfo;

    private int httpResponseCode;
}
