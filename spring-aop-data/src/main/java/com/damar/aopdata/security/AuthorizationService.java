package com.damar.aopdata.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationService {

    public void checkAccessAuthorization(){
        log.info("CHECK api access authorization...");
        log.info("CHECK api access: AUTHORIZED");
    }
}
