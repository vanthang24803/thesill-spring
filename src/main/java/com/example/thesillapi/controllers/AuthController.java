package com.example.thesillapi.controllers;

import com.example.thesillapi.dtos.message.Response;
import com.example.thesillapi.entities.Role;
import com.example.thesillapi.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public Response<List<Role>> seedRoles() {
        return roleService.createSeedRole();
    }


    @PostMapping(path = "register")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<?> register() {
        return null;
    }

}
