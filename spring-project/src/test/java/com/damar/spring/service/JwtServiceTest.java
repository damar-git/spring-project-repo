package com.damar.spring.service;

import com.damar.spring.config.JwtTestConfig;
import com.damar.spring.service.security.JwtService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(
        classes = {JwtTestConfig.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtServiceTest {

    @Spy
    private JwtService jwtService;

    private String issuer = "app-issuer";

    private String secret = "secret";

    @BeforeAll
    void before() throws Exception {
        FieldUtils.writeField(jwtService, "ISSUER", this.issuer, true);
        FieldUtils.writeField(jwtService, "SECRET_KEY", this.secret, true);
    }

    @Test
    void whenGivenParamsThenCreateAndDecodeCorrectJwt() {

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
