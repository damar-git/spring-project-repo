package com.damar.aopdata.api;

import com.damar.aopdata.model.UserServiceResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/user-api")
public interface UserApi {

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserServiceResponse> getUserById(@RequestParam(value = "userId", required = true) Long userId);

}
