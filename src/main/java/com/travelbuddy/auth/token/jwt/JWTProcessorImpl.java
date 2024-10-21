package com.travelbuddy.auth.token.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@RequiredArgsConstructor
public class JWTProcessorImpl implements JWTProcessor{
    private final Algorithm algorithm;

    private final long tokenLifespanMs;

    @Override
    public JWTBuilder getBuilder() {
        long now = System.currentTimeMillis();

        JWTCreator.Builder jwtCreate = JWT.create()
                .withIssuer("auth0")
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + tokenLifespanMs));

        return new JWTBuilderImpl(jwtCreate, algorithm);
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
