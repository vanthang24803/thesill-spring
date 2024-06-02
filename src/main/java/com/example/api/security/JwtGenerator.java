package com.example.api.security;

import com.example.api.common.exceptions.UnauthorizedException;
import com.example.api.domain.dtos.auth.RoleDto;
import com.example.api.domain.dtos.auth.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtGenerator {
    private static final SecretKey secretKey =
            Keys.hmacShaKeyFor(System.getenv("SECRET_KEY").getBytes(StandardCharsets.UTF_8));

    public String generateAccessToken(UserDto account) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + Constants.AC_EXP * 1000);

        return generateToken(account, currentDate, expireDate);
    }

    public String generateRefreshToken(UserDto account) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + Constants.RF_EXP * 1000);

        return generateToken(account, currentDate, expireDate);
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

        } catch (Exception ex) {
            throw new UnauthorizedException();
        }
    }

    public Claims decodeToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(UserDto account, Date currentDate, Date expireDate) {

        return Jwts.builder()
                .setSubject(account.getEmail())
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .claim("roles", getStringRole(account.getRoles()))
                .compact();
    }

    private List<String> getStringRole(List<RoleDto> data) {
        List<String> roles = new ArrayList<>();
        data.forEach(role -> roles.add(role.getName().toString()));
        return roles;
    }
}