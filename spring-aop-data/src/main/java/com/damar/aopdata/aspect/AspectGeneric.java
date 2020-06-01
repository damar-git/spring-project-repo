package com.damar.aopdata.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static com.damar.aopdata.constants.AspectConstants.ACCESS_LOG;

@Component
@Aspect
@Slf4j
public class AspectGeneric {

    /**
     * Action taken before a specific annotation Pointcut match
     * @param joinPoint Provides access to information about the Joinpoint
     */
    @Before("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut(JoinPoint joinPoint) {
        log.info(ACCESS_LOG + " Spring component");
    }

}
