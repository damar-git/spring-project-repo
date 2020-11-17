package com.damar.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.damar.spring.constants.AspectConstants.*;

@Component
@Aspect
@Slf4j
public class AspectRepositoryLayer {

    /**
     * Pointcut matching every method that strats with "delete"
     * within the com.damar.spring.repository package
     */
    @Pointcut("execution(* com.damar.spring.repository..delete*(..))")
    public void deleteRepositoryMethodExecution() {
    }

    /**
     * Pointcut matching every method that strats with "find"
     * within the com.damar.spring.repository package
     */
    @Pointcut("execution(* com.damar.spring.repository..find*(..))")
    public void findRepositoryMethodExecution() {
    }

    /**
     * Pointcut matching every method within the com.damar.spring.repository package
     */
    @Pointcut("execution (* com.damar.spring.repository..*(..))")
    public void repositoryExecution(){
    }

    /**
     * Action taken before the repositoryExecution() Pointcut match
     * @param joinPoint Provides access to information about the Joinpoint
     */
    @Before("repositoryExecution()")
    public void accessToRepository(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();

        log.info(ACCESS_LOG + " repository: {} | args: {}", methodName, args);
    }

    /**
     * Action taken around the findRepositoryMethodExecution() Pointcut match
     * @param joinPoint Provides access to information about the Joinpoint
     */
    @Around("findRepositoryMethodExecution()")
    public Object aroundFindRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();

        log.info(ACCESS_LOG + " data FIND operation: {} | args: {}", methodName, args);
        Object result = null;
        try {
            result = joinPoint.proceed();
            log.info(END_LOG + " repository method: {} | result: {}", methodName, result);
        } catch (Exception e){
            log.error(ERROR_LOG + " AOP aroundFindRepository: ", e);
        }
        return result;
    }

    /**
     * Action taken around the deleteRepositoryMethodExecution() Pointcut match
     * @param joinPoint Provides access to information about the Joinpoint
     */
    @Around("deleteRepositoryMethodExecution()")
    public Object aroundDeleteRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String args = Arrays.asList(joinPoint.getArgs()).toString();

        log.info(WARNING_LOG + " access to a data DELETE operation: {} | args: {}", methodName, args);
        Object result = null;
        try {
            result = joinPoint.proceed();
            log.info(END_LOG + " repository method: {} | result: {}", methodName, result);
        } catch (Exception e){
            log.error(ERROR_LOG + " AOP aroundFindRepository: ", e);
        }
        return result;
    }

}
