package com.travelbuddy.common.exception.auth;

public class InvalidLoginCredentialsException extends RuntimeException{
    public InvalidLoginCredentialsException(String message){
        super(message);
    }

    public InvalidLoginCredentialsException() {

    }
}
