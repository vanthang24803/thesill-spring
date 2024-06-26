package com.example.api.services;

import com.example.api.domain.dtos.auth.LoginDto;
import com.example.api.domain.dtos.auth.RegisterDto;
import com.example.api.domain.dtos.auth.UserResponse;
import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.token.RefreshTokenDto;
import com.example.api.domain.dtos.token.TokenResponse;

public interface UserService {
    Response<UserResponse> register(RegisterDto registerDto);

    Response<TokenResponse> login(LoginDto loginDto);

    Response<TokenResponse> refreshToken(RefreshTokenDto refreshToken);
}
