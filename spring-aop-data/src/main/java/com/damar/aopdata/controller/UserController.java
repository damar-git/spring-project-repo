package com.damar.aopdata.controller;


import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.api.UserApi;
import com.damar.aopdata.model.User;
import com.damar.aopdata.model.response.UserServiceAllResponse;
import com.damar.aopdata.model.response.UserServiceResponse;
import com.damar.aopdata.service.UserService;
import com.damar.aopdata.utils.UserServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<UserServiceResponse> getUserById(Long userId) {
        UserServiceResponse userServiceResponse = new UserServiceResponse();
        try {
            userServiceResponse.setUser(userService.getById(userId));
        } catch (UserNotFoundException e) {
            userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.NOT_FOUND));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userServiceResponse);
        } catch (Exception e) {
            userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userServiceResponse);
        }
        userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.OK));
        return ResponseEntity.ok(userServiceResponse);
    }

    @Override
    public ResponseEntity<UserServiceAllResponse> getAllUser(Integer age, String name, Boolean isActive) {
        UserServiceAllResponse userServiceAllResponse = new UserServiceAllResponse();
        try {
            userServiceAllResponse.setUserList(userService.getAll(age, name, isActive));
        } catch (Exception e) {
            userServiceAllResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(userServiceAllResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userServiceAllResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(userServiceAllResponse, HttpStatus.OK);
    }

    public ResponseEntity<UserServiceResponse> updateUser(User user) {
        UserServiceResponse userServiceResponse = new UserServiceResponse();
        try {
            userServiceResponse.setUser(userService.updateUser(user));
        } catch (UserNotFoundException e) {
            userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(userServiceResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(userServiceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(userServiceResponse, HttpStatus.OK);
    }

    public ResponseEntity<UserServiceResponse> saveUser(User user) {
        UserServiceResponse userServiceResponse = new UserServiceResponse();
        try {
            userServiceResponse.setUser(userService.saveUser(user));
        } catch (Exception e) {
            userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(userServiceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(userServiceResponse, HttpStatus.OK);
    }

    public ResponseEntity<UserServiceResponse> deleteUser(Long id) {
        UserServiceResponse userServiceResponse = new UserServiceResponse();
        try {
            userService.deleteUser(id);
        } catch (UserNotFoundException e) {
            userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(userServiceResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(userServiceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userServiceResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(userServiceResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserServiceAllResponse> getAllUserByMinMaxAge(Integer maxAge, Integer minAge, Boolean equal) {
        UserServiceAllResponse userServiceAllResponse = new UserServiceAllResponse();
        try {
            userServiceAllResponse.setUserList(userService.getAllByMinMaxAge(minAge, maxAge, equal));
        } catch (Exception e){
            userServiceAllResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(userServiceAllResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userServiceAllResponse.setUserServiceOutcome(UserServiceUtils.buildOutcome(HttpStatus.OK));
        return new ResponseEntity<>(userServiceAllResponse, HttpStatus.OK);
    }

}
