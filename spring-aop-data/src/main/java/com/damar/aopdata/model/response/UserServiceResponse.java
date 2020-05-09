package com.damar.aopdata.model.response;

import com.damar.aopdata.model.User;
import lombok.Data;

@Data
public class UserServiceResponse {

    private User user;

    private UserServiceOutcome userServiceOutcome;

}
