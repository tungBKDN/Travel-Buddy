package com.travelbuddy.auth.token.jwt;

public interface JWTProcessor {
    JWTBuilder getBuilder(boolean isRefreshToken);

    JWTBuilder getBuilder();
    /**
     * @throws InvalidJWTException if token is invalid
     */
    VerifiedJWT getVerifiedJWT(String token);
}
