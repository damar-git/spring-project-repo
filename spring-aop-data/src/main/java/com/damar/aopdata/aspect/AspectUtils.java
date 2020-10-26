package com.damar.aopdata.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

public class AspectUtils {

    private AspectUtils() {

    }

    public static String getAspectMethodParameterValueByName(JoinPoint joinPoint, String parameterName) {
        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        String[] parametersName = methodSig.getParameterNames();

        int tokenParamIndex = Arrays.asList(parametersName).indexOf(parameterName);
        return args[tokenParamIndex].toString();
    }
}
