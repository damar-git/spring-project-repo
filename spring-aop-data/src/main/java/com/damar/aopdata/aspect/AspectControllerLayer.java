package com.damar.aopdata.aspect;

import com.damar.aopdata.service.security.AuthorizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.damar.aopdata.constants.AspectConstants.*;

@Component
@Aspect
@Slf4j
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AspectControllerLayer {

    private AuthorizationService playerServiceSecurity;

    /**
     * Pointcut matching every execution within the com.damar.aopdata.controller package
     */
    @Pointcut("within(com.damar.aopdata.controller..*)")
    public void controllerExecution(){
    }

    /**
     * Pointcut matching every getPlayerById execution within PlayerController class
     */
    @Pointcut("execution(* com.damar.aopdata.controller.PlayerController.getPlayerById(..))")
    public void getPlayerByIdExecution(){
    }

    /**
     * Action taken before the controllerExecution() Pointcut match
     * @param joinPoint Provides access to information about the Joinpoint
     */
    @Before("controllerExecution()")
    public void beforeExecution(JoinPoint joinPoint){
        playerServiceSecurity.checkAccessAuthorization();

        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info(START_LOG + " method: {} | args: {}", methodName, args);
    }

    /**
     * Action taken after the controllerExecution() Pointcut match
     * @param joinPoint Provides access to information about the Joinpoint
     */
    @After("controllerExecution()")
    public void afterExecution(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info(END_LOG + " method: {} | args: {}", methodName, args);
    }

    /**
     * Action taken before the getPlayerByIdExecution() Pointcut match
     * @param joinPoint Provides access to information about the Joinpoint
     */
    @Before("getPlayerByIdExecution()")
    public void beforeGetPlayerByIdExecution(JoinPoint joinPoint){
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info(ACCESS_LOG + " method: getPlayerById | args: {}", args);
    }

}
