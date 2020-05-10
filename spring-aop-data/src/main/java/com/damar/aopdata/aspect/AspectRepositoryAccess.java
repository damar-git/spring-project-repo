package com.damar.aopdata.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class AspectRepositoryAccess {

    @Before("execution(* com.damar.aopdata.repository..delete*(..))")
    public void deleteMethodExecution(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info("WARNING, access to a data DELETE operation: {} | args: {}", methodName, args);
    }

    @Before("execution(* com.damar.aopdata.repository..find*(..))")
    public void findMethodExecution(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();
        log.info("ACCESS to a data FIND operation: {} | args: {}", methodName, args);
    }

}
