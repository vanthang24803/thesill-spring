package com.example.thesillapi.controllers;

import com.example.thesillapi.domain.dtos.message.Response;
import com.example.thesillapi.services.RoleService;
import com.example.thesillapi.domain.entities.RoleEntity;
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

    @GetMapping(path = "/roles")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response<List<RoleEntity>>> seedRoles() {
        return new ResponseEntity<>(roleService.createSeedRole(), HttpStatus.OK);
    }


    @PostMapping(path = "register")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<?> register() {
        return null;
    }

}
