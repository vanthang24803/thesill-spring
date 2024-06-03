package com.example.api.controllers;

import com.example.api.domain.dtos.auth.LoginDto;
import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.auth.UserDto;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.token.RefreshTokenDto;
import com.example.api.domain.dtos.token.TokenResponse;
import com.example.api.services.RoleService;
import com.example.api.domain.entities.RoleEntity;
import com.example.api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/auth")
public class AuthController {

    private final RoleService roleService;
    private final UserService userService;


    @GetMapping(path = "/roles")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response<List<RoleEntity>>> seedRoles() {
        return new ResponseEntity<>(roleService.createSeedRole(), HttpStatus.OK);
    }

    @PostMapping(path = "register")
    public ResponseEntity<Response<UserDto>> register(@RequestBody @Valid RegisterDto register) {
        return new ResponseEntity<>(userService.register(register), HttpStatus.CREATED);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Response<TokenResponse>> login(@RequestBody @Valid LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping(path = "refresh")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenDto token) {
        return new ResponseEntity<>(userService.refreshToken(token), HttpStatus.OK);
    }

}
