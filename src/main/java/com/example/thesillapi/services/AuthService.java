package com.example.thesillapi.services;

import com.example.thesillapi.domain.dtos.auth.RegisterDto;
import com.example.thesillapi.domain.dtos.message.Response;

public interface AuthService {
    Response<?> save(RegisterDto registerDto);
}
