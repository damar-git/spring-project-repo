package com.damar.aopdata.service;


import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.model.User;

import java.util.List;

public interface UserService {

    User getById(Long id) throws UserNotFoundException;

    List<User> getAll(Integer age, Boolean isActive);

    User updateUser(User user) throws UserNotFoundException;

    User saveUser(User user);

    void deleteUser(Long id) throws UserNotFoundException;

    List<User> getAllByMinMaxAge(Integer minAge, Integer maxAge, Boolean equal);

    List<User> getUserByName(String name);

    List<User> getAllUserByAgeAndActive(Integer age, Boolean isActive);
}
