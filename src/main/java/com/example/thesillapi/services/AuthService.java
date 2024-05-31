package com.example.thesillapi.services;

import com.example.thesillapi.dtos.auth.RegisterDto;
import com.example.thesillapi.dtos.message.Response;

public interface AuthService {
    Response<?> save(RegisterDto registerDto);
}
