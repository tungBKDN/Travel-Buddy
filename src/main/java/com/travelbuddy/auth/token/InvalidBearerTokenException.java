package com.travelbuddy.auth.token;

import org.springframework.security.core.AuthenticationException;

public class InvalidBearerTokenException extends AuthenticationException {
    public InvalidBearerTokenException(String msg) {
        super(msg);
    }
}
