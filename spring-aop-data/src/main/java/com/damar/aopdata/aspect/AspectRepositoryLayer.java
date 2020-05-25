package com.damar.aopdata.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.damar.aopdata.constants.AspectConstants.*;

@Component
@Aspect
@Slf4j
public class AspectRepositoryLayer {

    @Pointcut("execution(* com.damar.aopdata.repository..delete*(..))")
    public void deleteRepositoryMethodExecution() {
    }

    @Pointcut("execution(* com.damar.aopdata.repository..find*(..))")
    public void findRepositoryMethodExecution() {
    }

    @Pointcut("execution (* com.damar.aopdata.repository..*(..))")
    public void repositoryExecution(){
    }

    @Before("repositoryExecution()")
    public void accessToRepository(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();

        log.info(ACCESS_LOG + " repository: {} | args: {}", methodName, args);
    }

    @Around("findRepositoryMethodExecution()")
    public Object aroundFindRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();

        log.info(ACCESS_LOG + " data FIND operation: {} | args: {}", methodName, args);
        Object result = null;
        try {
            result = joinPoint.proceed();
            log.info(END_LOG + " method: {} | result: {}", methodName, result);
        } catch (Exception e){
            log.error(ERROR_LOG + " AOP aroundFindRepository: ", e);
        }
        return result;
    }

    @Around("deleteRepositoryMethodExecution()")
    public Object aroundDeleteRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();

        log.info(WARNING_LOG + " access to a data DELETE operation: {} | args: {}", methodName, args);
        Object result = null;
        try {
            result = joinPoint.proceed();
            log.info(END_LOG + "+ method: {} | result: {}", methodName, result);
        } catch (Exception e){
            log.error(ERROR_LOG + " AOP aroundFindRepository: ", e);
        }
        return result;
    }

}
