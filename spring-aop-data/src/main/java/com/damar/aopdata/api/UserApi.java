package com.damar.aopdata.api;

import com.damar.aopdata.configuration.SwaggerConfig;
import com.damar.aopdata.model.User;
import com.damar.aopdata.model.response.UserServiceAllResponse;
import com.damar.aopdata.model.response.UserServiceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.TAG_1})
@RequestMapping("/user-api")
public interface UserApi {

    @ApiOperation(value = "Retrieve an user")
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> getUserById(@ApiParam(value = "the user id", required = true)
                                                    @PathVariable(value = "id") Long userId);

    @ApiOperation(value = "Update an user")
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> updateUser(@ApiParam(value = "the user object", required = true)
                                                   @RequestBody User user);

    @ApiOperation(value = "Save an user")
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> saveUser(@ApiParam(value = "the user object", required = true)
                                                 @RequestBody User user);

    @ApiOperation(value = "Delete an user")
    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceResponse> deleteUser(@ApiParam(value = "the user id", required = true)
                                                   @PathVariable(value = "id") Long userId);

    @ApiOperation(value = "Retrieve all users")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceAllResponse> getAllUser(@ApiParam(value = "the user age")
                                                      @RequestParam(value = "age", required = false) Integer age,
                                                      @ApiParam(value = "the user name")
                                                      @RequestParam(value = "name", required = false) String name,
                                                      @ApiParam(value = "indicates whether the user should be active or not")
                                                      @RequestParam(value = "isActive", required = false) Boolean isActive);

    @ApiOperation(value = "Retrieve all users by minimum and maximum age")
    @GetMapping(value = "/all/age", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserServiceAllResponse> getAllUserByMinMaxAge(@ApiParam(value = "the user max age", required = true)
                                                                 @RequestParam(value = "maxAge") Integer maxAge,
                                                                 @ApiParam(value = "the user min age", required = true)
                                                                 @RequestParam(value = "minAge") Integer minAge,
                                                                 @ApiParam(value = "indicates whether the age input value should be included or not", required = true)
                                                                 @RequestParam(value = "equal") Boolean equal);

}
