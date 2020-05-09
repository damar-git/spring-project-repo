package com.damar.aopdata.api;

import com.damar.aopdata.model.User;
import com.damar.aopdata.model.response.UserServiceAllResponse;
import com.damar.aopdata.model.response.UserServiceResponse;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/user-api")
public interface UserApi {

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> getUserById(@ApiParam(value = "the user id", name = "userId")
                                                    @RequestParam(value = "userId") Long userId);

    @GetMapping(value = "/user/username", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> getUserByUsername(@ApiParam(value = "the user username", name = "username")
                                                    @RequestParam(value = "username") String username);

    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> updateUser(@ApiParam(value = "the user object", name = "user")
                                                   @RequestBody User user);

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> saveUser(@ApiParam(value = "the user object", name = "user")
                                                 @RequestBody User user);

    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> deleteUser(@ApiParam(value = "the user id", name = "userId")
                                                   @RequestParam(value = "userId") Long userId);

    @GetMapping(value = "/user/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceAllResponse> getAllUser(@ApiParam(value = "indicates whether the user should be active or not",
            name = "isActive") @RequestParam(value = "isActive", required = false) Boolean isActive);

    @GetMapping(value = "/user/all/age", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceAllResponse> getAllByMinMaxAge(@ApiParam(value = "the user max age", name = "maxAge")
                                                             @RequestParam(value = "maxAge") Integer maxAge,
                                                             @ApiParam(value = "the user min age", name = "minAge")
                                                             @RequestParam(value = "minAge") Integer minAge,
                                                             @ApiParam(value = "indicates whether the age input value should be included or not",
                                                                     name = "equal")
                                                             @RequestParam(value = "equal") Boolean equal);

}
