package com.damar.spring.exception;


public class JwtNotValidException extends RuntimeException {

    public JwtNotValidException() {
    }

    public JwtNotValidException(String message) {
        super(message);
    }
}
