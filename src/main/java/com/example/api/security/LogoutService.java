package com.example.api.security;

import com.example.api.repositories.AuthRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final AuthRepository authRepository;

    private final JwtGenerator jwtGenerator;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        jwt = authHeader.substring(7);

        var decode = jwtGenerator.decodeToken(jwt);


        authRepository.findByEmail(decode.getSubject()).ifPresent(user -> {
            user.setRefreshToken(null);
            authRepository.save(user);
            log.info("User logout successfully: {}", user.getEmail());
        });
        SecurityContextHolder.clearContext();
    }
}
