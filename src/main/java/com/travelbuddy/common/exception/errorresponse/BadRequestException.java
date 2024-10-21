package com.travelbuddy.common.exception.errorresponse;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
