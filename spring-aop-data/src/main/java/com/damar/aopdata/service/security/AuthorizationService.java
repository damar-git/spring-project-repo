package com.damar.aopdata.service.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationService {

    public void checkAccessAuthorization(){
        log.info("[CHECK] Api access: checking authorization...");
        log.info("[CHECK] Api access: AUTHORIZED");
    }
}
