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
    private AuthorizationService userServiceSecurity;

    @Before("within(com.damar.aopdata.controller..*)")
    public void beforeExecution(JoinPoint joinPoint){
        userServiceSecurity.checkAccessAuthorization();

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

    @Before("execution(* com.damar.aopdata.controller.UserController.getUserById(..))")
    public void getUserByIdExecution(JoinPoint joinPoint){
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info("ACCESS to method: getUserById | args: {}", args);
    }

}
