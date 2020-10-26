package com.damar.aopdata.exception;

import com.damar.aopdata.model.response.PlayerServiceResponse;
import com.damar.aopdata.utils.OutcomeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PlayerServiceResponse> handleException(Exception e,
                                                                 HandlerMethod handlerMethod){
        String className = handlerMethod.getMethod().getDeclaringClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        log.error("[{} | {}] Exception: ", className, methodName, e);
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(playerServiceResponse);
    }
}
