package com.damar.spring.service;

import com.damar.spring.config.JwtTestConfig;
import com.damar.spring.service.security.JwtService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(
        classes = {JwtTestConfig.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Value("${jwt.issuer}")
    private String issuer;

    @Test
    void when_given_params_then_create_and_decode_correct_jwt() {

        //given
        String username = "damar";

        //when
        String jwt = jwtService.createJWT(username);
        Claims claims = jwtService.decodeJWT(jwt);

        //then
        Assertions.assertEquals(username, claims.getSubject());
        Assertions.assertEquals(issuer, claims.getIssuer());
    }

}
