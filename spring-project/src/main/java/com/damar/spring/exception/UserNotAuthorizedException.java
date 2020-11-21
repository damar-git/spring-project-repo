package com.damar.spring.exception;


public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException() {
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
