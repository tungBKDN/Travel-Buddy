package com.travelbuddy.auth.token.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.travelbuddy.auth.token.jwt.InvalidJWTException;
import com.travelbuddy.auth.token.jwt.JWTBuilder;
import com.travelbuddy.auth.token.jwt.JWTProcessor;
import com.travelbuddy.auth.token.jwt.VerifiedJWT;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@RequiredArgsConstructor
public class JWTProcessorImpl implements JWTProcessor {
    private final Algorithm algorithm;

    private final long tokenLifespanMs;

    @Override
    public JWTBuilder getBuilder(boolean isRefreshToken) {
        long now = System.currentTimeMillis();

        long expiration = isRefreshToken ? tokenLifespanMs * 30 : tokenLifespanMs;

        JWTCreator.Builder jwtCreate = JWT.create()
                .withIssuer("auth0")
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + expiration));

        return new JWTBuilderImpl(jwtCreate, algorithm);
    }

    @Override
    public JWTBuilder getBuilder() {
        return getBuilder(false);
    }

    @Override
    public VerifiedJWT getVerifiedJWT(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();

            return new VerifiedJWTImpl(verifier.verify(token));
        } catch (JWTVerificationException e) {
            throw new InvalidJWTException(e.getMessage());
        }
    }
}
