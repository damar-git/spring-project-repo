package com.damar.aopdata.service.security;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationService {

    @Autowired
    public AuthorizationService(JwtService jwtService){
        this.jwtService = jwtService;
    }

    private JwtService jwtService;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.issuer}")
    private String ISSUER;

    public void checkAccessAuthorization(String jwt){
        log.info("[CHECK] Api access: checking authorization...");

        Claims claims = jwtService.decodeJWT(jwt);
        String issuer = claims.getIssuer();
        String subject = claims.getSubject();

        if (!StringUtils.equals(ISSUER, issuer))
            throw new RuntimeException();

        log.info("[CHECK] Api access: User {} AUTHORIZED", subject);
    }
}
