package com.example.api.services.ipml;

import com.example.api.common.exceptions.BadRequestException;
import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.auth.LoginDto;
import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.auth.UserDto;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.token.TokenResponse;
import com.example.api.domain.entities.AuthEntity;
import com.example.api.domain.entities.RoleEntity;
import com.example.api.domain.enums.RoleEnum;
import com.example.api.repositories.AuthRepository;
import com.example.api.repositories.RoleRepository;
import com.example.api.security.JwtGenerator;
import com.example.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceIpml implements UserService {
    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper<AuthEntity, UserDto> userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @Override
    public Response<UserDto> register(RegisterDto registerDto) {
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

        UserDto response = userMapper.mapTo(auth);

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

        String accessToken = jwtGenerator.generateAccessToken(loginDto.getEmail());
        String refreshToken = jwtGenerator.generateRefreshToken(loginDto.getEmail());

        user.setRefreshToken(refreshToken);

        authRepository.save(user);

        TokenResponse token = new TokenResponse(
                refreshToken, accessToken

        );

        return new Response<>(HttpStatus.OK.value(), token);
    }
}
