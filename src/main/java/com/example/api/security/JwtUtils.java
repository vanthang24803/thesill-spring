package com.example.api.security;

import com.example.api.domain.dtos.auth.RoleDto;
import com.example.api.domain.dtos.auth.UserResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtUtils {
    private static final SecretKey secretKey =
            Keys.hmacShaKeyFor(System.getenv("SECRET_KEY").getBytes(StandardCharsets.UTF_8));

    public String generateAccessToken(UserResponse account) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + Constants.AC_EXP * 1000);

        return generateToken(account, currentDate, expireDate);
    }

    public String generateRefreshToken(UserResponse account) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + Constants.RF_EXP * 1000);

        return generateToken(account, currentDate, expireDate);
    }

    public String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect",
                    ex.fillInStackTrace());
        }
    }

    public Claims decodeToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(UserResponse account, Date currentDate, Date expireDate) {

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