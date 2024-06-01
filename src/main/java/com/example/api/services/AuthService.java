package com.example.api.services;

import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.message.Response;

public interface AuthService {
    Response<?> save(RegisterDto registerDto);
}
