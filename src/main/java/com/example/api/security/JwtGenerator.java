package com.example.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateAccessToken(String email) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + Constants.AC_EXP * 1000);

        return buildToken(email, currentDate, expireDate);
    }

    public String generateRefreshToken(String email) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + Constants.RF_EXP * 1000);

        return buildToken(email, currentDate, expireDate);
    }

    private String buildToken(String email, Date currentDate, Date expireDate) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}