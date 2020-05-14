package com.damar.aopdata.aspect;

import com.damar.aopdata.security.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class AspectControllerLayer {

    @Autowired
    private AuthorizationService playerServiceSecurity;

    @Before("within(com.damar.aopdata.controller..*)")
    public void beforeExecution(JoinPoint joinPoint){
        playerServiceSecurity.checkAccessAuthorization();

        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();

        log.info("START method: {} | args: {}", methodName, args);
    }

    @After("within(com.damar.aopdata.controller..*)")
    public void afterExecution(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info("END method: {} | args: {}", methodName, args);
    }

    @Before("execution(* com.damar.aopdata.controller.PlayerController.getPlayerById(..))")
    public void getPlayerByIdExecution(JoinPoint joinPoint){
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info("ACCESS to method: getPlayerById | args: {}", args);
    }

}
