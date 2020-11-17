package com.damar.spring.service;

import com.damar.spring.exception.UserNotFoundException;
import com.damar.spring.model.User;

public interface UserService {

    User signUp(User user);

    String login(String username) throws UserNotFoundException;
}
