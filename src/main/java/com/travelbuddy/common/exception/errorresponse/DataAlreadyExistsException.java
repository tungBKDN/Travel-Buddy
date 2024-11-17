package com.travelbuddy.common.exception.errorresponse;

public class DataAlreadyExistsException extends RuntimeException{
    public DataAlreadyExistsException(String message) {
        super(message);
    }
}
