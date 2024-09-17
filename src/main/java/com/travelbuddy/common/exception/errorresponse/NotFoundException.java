package com.travelbuddy.common.exception.errorresponse;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
