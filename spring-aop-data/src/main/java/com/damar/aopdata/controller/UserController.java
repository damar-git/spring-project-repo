package com.damar.aopdata.controller;


import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.api.UserApi;
import com.damar.aopdata.model.UserServiceResponse;
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

}
