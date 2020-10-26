package com.damar.aopdata.model.response;

import lombok.Data;

@Data
public class ServiceOutcome {

    private String additionalInfo;

    private Integer httpResponseCode;
}
