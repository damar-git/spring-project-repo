package com.damar.aopdata.api;

import com.damar.aopdata.model.UserServiceResponse;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/user-api")
public interface UserApi {

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> getUserById(@ApiParam(value = "the user id", name="userId")
                                                    @RequestParam(value = "userId", required = true) Long userId);

}
