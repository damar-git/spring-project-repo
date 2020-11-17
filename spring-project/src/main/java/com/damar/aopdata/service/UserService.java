package com.damar.aopdata.service;

import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.model.User;

public interface UserService {

    User signUp(User user);

    String login(String username) throws UserNotFoundException;
}
