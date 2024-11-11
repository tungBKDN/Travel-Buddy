package com.travelbuddy.common.exception.errorresponse;

public class BadTimingInputException extends RuntimeException {
    private String cause;

    public BadTimingInputException(String message) {
        super(message);
    }

    public BadTimingInputException(String cause, String message) {
        super(message);
        cause = cause;
    }
}
