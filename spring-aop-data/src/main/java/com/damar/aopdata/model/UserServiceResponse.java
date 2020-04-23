package com.damar.aopdata.model;

import lombok.Data;

@Data
public class UserServiceResponse {

    private User user;

    private UserServiceOutcome userServiceOutcome;

}
