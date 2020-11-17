package com.damar.spring.exception;


public class PlayerNotFoundException extends Exception {

    public PlayerNotFoundException() {
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
