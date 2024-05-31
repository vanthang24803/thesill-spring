package com.example.thesillapi.services.ipml;

import com.example.thesillapi.dtos.auth.RegisterDto;
import com.example.thesillapi.dtos.message.Response;
import com.example.thesillapi.repositories.AuthRepository;
import com.example.thesillapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceIpml implements AuthService {

    private final AuthRepository authRepository;


    @Override
    public Response<?> save(RegisterDto registerDto) {

        return null;
    }
}
