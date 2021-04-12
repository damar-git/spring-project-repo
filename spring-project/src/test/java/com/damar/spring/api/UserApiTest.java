package com.damar.spring.api;

import com.damar.spring.controller.UserController;
import com.damar.spring.model.User;
import com.damar.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserApiTest {

    private static final String USER_API_PATH = "/user-api";

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void when_call_signup_then_return_user() throws Exception {

        // when

        when(userService.signUp(any(User.class))).thenReturn(new User());

        // then

        mockMvc.perform(post(USER_API_PATH + "/user/{username}/sign-up",
                "ZXC"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceOutcome").exists())
                .andExpect(jsonPath("$.serviceOutcome.additionalInfo")
                        .value("OK"))
                .andExpect(jsonPath("$.serviceOutcome.httpResponseCode")
                        .value(200));
    }

    @Test
    void when_call_login_then_return_token() throws Exception {

        // given

        String token = "12345";

        // when

        when(userService.login("ZXC")).thenReturn(token);

        // then

        mockMvc.perform(get(USER_API_PATH + "/user/{username}/login",
                "ZXC"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authToken").value(token))
                .andExpect(jsonPath("$.serviceOutcome").exists())
                .andExpect(jsonPath("$.serviceOutcome.additionalInfo")
                        .value("OK"))
                .andExpect(jsonPath("$.serviceOutcome.httpResponseCode")
                        .value(200));
    }
}
