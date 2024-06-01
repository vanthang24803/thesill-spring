package com.example.api.services.ipml;

import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.message.Response;
import com.example.api.repositories.AuthRepository;
import com.example.api.services.AuthService;
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
