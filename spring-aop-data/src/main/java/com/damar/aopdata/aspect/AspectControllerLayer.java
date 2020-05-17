package com.damar.aopdata.aspect;

import com.damar.aopdata.security.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.damar.aopdata.constants.AspectConstants.*;

@Component
@Aspect
@Slf4j
public class AspectControllerLayer {

    @Autowired
    private AuthorizationService playerServiceSecurity;

    @Pointcut("within(com.damar.aopdata.controller..*)")
    public void controllerExecution(){
    }

    @Pointcut("execution(* com.damar.aopdata.controller.PlayerController.getPlayerById(..))")
    public void getPlayerByIdExecution(){
    }

    @Before("controllerExecution()")
    public void beforeExecution(JoinPoint joinPoint){
        playerServiceSecurity.checkAccessAuthorization();

        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info(START_LOG + " method: {} | args: {}", methodName, args);
    }

    @After("controllerExecution()")
    public void afterExecution(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info(END_LOG + " method: {} | args: {}", methodName, args);
    }

    @Before("getPlayerByIdExecution()")
    public void beforeGetPlayerByIdExecution(JoinPoint joinPoint){
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info(ACCESS_LOG + " method: getPlayerById | args: {}", args);
    }

}
