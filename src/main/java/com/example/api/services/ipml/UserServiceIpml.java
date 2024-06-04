package com.example.api.services.ipml;

import com.example.api.common.exceptions.BadRequestException;
import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.auth.LoginDto;
import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.auth.UserResponse;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.token.RefreshTokenDto;
import com.example.api.domain.dtos.token.TokenResponse;
import com.example.api.domain.entities.AuthEntity;
import com.example.api.domain.entities.RoleEntity;
import com.example.api.domain.enums.RoleEnum;
import com.example.api.repositories.AuthRepository;
import com.example.api.repositories.RoleRepository;
import com.example.api.security.JwtUtils;
import com.example.api.services.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceIpml implements UserService {
    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper<AuthEntity, UserResponse> userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public Response<UserResponse> register(RegisterDto registerDto) {
        if (authRepository.existsByEmail(registerDto.getEmail())) {
            throw new BadRequestException("Email Taken!");
        }

        String hashPassword = passwordEncoder.encode(registerDto.getPassword());

        RoleEntity role = roleRepository.findByName(RoleEnum.CUSTOMER)
                .orElseThrow(() -> new NotFoundException("Role not found!"));

        AuthEntity auth = new AuthEntity();
        auth.setEmail(registerDto.getEmail());
        auth.setFirstName(registerDto.getFirstName());
        auth.setLastName(registerDto.getLastName());
        auth.setPassword(hashPassword);
        auth.setId(UUID.randomUUID());

        auth.getRoles().add(role);

        auth = authRepository.save(auth);

        UserResponse response = userMapper.mapTo(auth);

        return new Response<>(HttpStatus.CREATED.value(), response);
    }

    @Override
    public Response<TokenResponse> login(LoginDto loginDto) {
        AuthEntity user = authRepository.findByEmail(loginDto.getEmail()).orElseThrow(
        );

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String email = loginDto.getEmail();

        AuthEntity account = authRepository.findByEmail(email).orElseThrow();

        UserResponse claim = userMapper.mapTo(account);

        String accessToken = jwtUtils.generateAccessToken(claim);
        String refreshToken = jwtUtils.generateRefreshToken(claim);

        user.setRefreshToken(refreshToken);

        authRepository.save(user);

        TokenResponse token = new TokenResponse(
                refreshToken, accessToken

        );

        return new Response<>(HttpStatus.OK.value(), token);
    }

    @Override
    public Response<TokenResponse> refreshToken(RefreshTokenDto request) {

        jwtUtils.validateToken(request.refreshToken);

        Claims claims = jwtUtils.decodeToken(request.refreshToken);

        AuthEntity account = authRepository.findByEmail(claims.getSubject()).orElseThrow();

        UserResponse claim = userMapper.mapTo(account);

        if (!account.getRefreshToken().equals(request.refreshToken)) {
            throw new NotFoundException("Refresh token not found");
        }

        TokenResponse token = new TokenResponse();

        if (claims.getExpiration().before(new Date())) {
            String accessToken = jwtUtils.generateAccessToken(claim);
            String refreshToken = jwtUtils.generateRefreshToken(claim);

            account.setRefreshToken(refreshToken);

            authRepository.save(account);

            token.accessToken = accessToken;
            token.refreshToken = refreshToken;

            return new Response<>(HttpStatus.OK.value(), token);
        }

        String accessToken = jwtUtils.generateAccessToken(claim);

        token.refreshToken = account.getRefreshToken();
        token.accessToken = accessToken;

        return new Response<>(HttpStatus.OK.value(), token);
    }

}
