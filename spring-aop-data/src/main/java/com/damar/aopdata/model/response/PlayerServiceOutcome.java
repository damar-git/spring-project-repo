package com.damar.aopdata.model.response;

import lombok.Data;

@Data
public class PlayerServiceOutcome {

    private String additionalInfo;

    private int httpResponseCode;
}
