package com.damar.aopdata.controller;

import com.damar.aopdata.api.UserApi;
import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.model.User;
import com.damar.aopdata.model.response.UserLoginResponse;
import com.damar.aopdata.model.response.UserSignUpResponse;
import com.damar.aopdata.service.UserService;
import com.damar.aopdata.utils.OutcomeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.damar.aopdata.constants.ErrorConstants.NO_USER_FOUND_ERROR_MSG;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserController implements UserApi {

    private UserService userService;

    @Override
    public ResponseEntity<UserSignUpResponse> signUp(String username) {
        UserSignUpResponse response = new UserSignUpResponse();

        User user = new User();
        user.setUsername(username);
        userService.signUp(user);

        response.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.OK));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<UserLoginResponse> login(String username) {
        UserLoginResponse response = new UserLoginResponse();
        try {
            response.setAuthToken(userService.login(username));
        } catch (UserNotFoundException ex) {
            log.info("User not found");
            response.setServiceOutcome(OutcomeUtils.buildOutcome(NO_USER_FOUND_ERROR_MSG,
                    HttpStatus.NOT_FOUND));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.OK));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
