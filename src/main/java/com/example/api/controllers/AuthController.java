package com.example.api.controllers;

import com.example.api.domain.dtos.auth.LoginDto;
import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.auth.UserResponse;
import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.token.RefreshTokenDto;
import com.example.api.domain.dtos.token.TokenResponse;
import com.example.api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping(path = "register")
    public ResponseEntity<Response<UserResponse>> register(@RequestBody @Valid RegisterDto register) {
        return new ResponseEntity<>(userService.register(register), HttpStatus.CREATED);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Response<TokenResponse>> login(@RequestBody @Valid LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping(path = "refresh")
    public ResponseEntity<Response<TokenResponse>> refreshToken(@RequestBody @Valid RefreshTokenDto token) {
        return new ResponseEntity<>(userService.refreshToken(token), HttpStatus.OK);
    }

}
