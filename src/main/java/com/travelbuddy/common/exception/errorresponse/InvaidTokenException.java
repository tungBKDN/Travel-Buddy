package com.travelbuddy.common.exception.errorresponse;

public class InvaidTokenException extends RuntimeException{
    public InvaidTokenException(String message) {
        super(message);
    }
}
