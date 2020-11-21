package com.damar.spring.exception;

import com.damar.spring.model.response.PlayerServiceResponse;
import com.damar.spring.utils.OutcomeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import static com.damar.spring.constants.ErrorConstants.EXCEPTION_HANDLER_LOGGER;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PlayerServiceResponse> handleException(Exception e,
                                                                 HandlerMethod handlerMethod){
        String className = handlerMethod.getMethod().getDeclaringClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        log.error(EXCEPTION_HANDLER_LOGGER, className, methodName, e);
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.INTERNAL_SERVER_ERROR));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(playerServiceResponse);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<PlayerServiceResponse> handleUserNotAuthorizedException(UserNotAuthorizedException e,
                                                                 HandlerMethod handlerMethod){
        String className = handlerMethod.getMethod().getDeclaringClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        log.error(EXCEPTION_HANDLER_LOGGER, className, methodName, e);
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.FORBIDDEN));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(playerServiceResponse);
    }

    @ExceptionHandler(JwtNotValidException.class)
    public ResponseEntity<PlayerServiceResponse> handleJwtNotValidException(JwtNotValidException e,
                                                                                  HandlerMethod handlerMethod){
        String className = handlerMethod.getMethod().getDeclaringClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        log.error(EXCEPTION_HANDLER_LOGGER, className, methodName, e);
        PlayerServiceResponse playerServiceResponse = new PlayerServiceResponse();
        playerServiceResponse.setServiceOutcome(OutcomeUtils.buildOutcome(HttpStatus.BAD_REQUEST));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(playerServiceResponse);
    }
}
