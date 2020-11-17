package com.damar.aopdata.model.response;

import lombok.Data;

@Data
public class UserLoginResponse {

    private String authToken;

    private ServiceOutcome serviceOutcome;
}
