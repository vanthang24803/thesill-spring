package com.example.api.controllers;

import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.message.Response;
import com.example.api.security.AuthService;
import com.example.api.services.RoleService;
import com.example.api.domain.entities.RoleEntity;
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
    private  final AuthService authService;

    @GetMapping(path = "/roles")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response<List<RoleEntity>>> seedRoles() {
        return new ResponseEntity<>(roleService.createSeedRole(), HttpStatus.OK);
    }

    @PostMapping(path = "register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto register) {
        return new ResponseEntity<>(authService.register(register), HttpStatus.CREATED);
    }

}
