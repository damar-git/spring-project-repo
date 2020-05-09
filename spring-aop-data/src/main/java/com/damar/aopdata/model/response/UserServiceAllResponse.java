package com.damar.aopdata.model.response;

import com.damar.aopdata.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserServiceAllResponse {

    private List<User> userList;

    private UserServiceOutcome userServiceOutcome;
}
