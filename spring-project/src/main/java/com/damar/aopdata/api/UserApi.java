package com.damar.aopdata.api;

import com.damar.aopdata.configuration.SwaggerConfig;
import com.damar.aopdata.model.response.UserLoginResponse;
import com.damar.aopdata.model.response.UserSignUpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = {SwaggerConfig.TAG_2})
@RequestMapping("/user-api")
public interface UserApi {

    @ApiOperation(value = "Sign-up new user")
    @PostMapping(value = "/sign-up/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserSignUpResponse> signUp(@ApiParam(value = "the username", required = true)
                                              @PathVariable(value = "username") String username);

    @ApiOperation(value = "Login to the application")
    @GetMapping(value = "/login/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserLoginResponse> login(@ApiParam(value = "the username", required = true)
                                                @PathVariable(value = "username") String username);
}
