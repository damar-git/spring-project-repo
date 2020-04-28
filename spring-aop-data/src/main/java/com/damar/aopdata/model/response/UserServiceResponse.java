package com.damar.aopdata.model.response;

import com.damar.aopdata.model.User;
import com.damar.aopdata.model.UserServiceOutcome;
import lombok.Data;

@Data
public class UserServiceResponse {

    private User user;

    private UserServiceOutcome userServiceOutcome;

}
