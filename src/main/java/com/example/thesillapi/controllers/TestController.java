package com.example.thesillapi.controllers;

import com.example.thesillapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class TestController {

    private final AuthService authService;


    @GetMapping(path = "/")

    public ResponseEntity<String> hello() {
        var result = authService.getHelloWorld();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
