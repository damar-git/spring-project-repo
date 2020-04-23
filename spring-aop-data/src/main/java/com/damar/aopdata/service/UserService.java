package com.damar.aopdata.service;


import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.model.User;

public interface UserService {

    User getById(Long id) throws UserNotFoundException;

}
