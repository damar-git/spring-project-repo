package com.damar.spring.service;

import com.damar.spring.config.JwtTestConfig;
import com.damar.spring.service.security.AuthorizationService;
import com.damar.spring.service.security.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(
        classes = {JwtTestConfig.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest(classes = JwtService.class)
class JwtServiceTest {

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
