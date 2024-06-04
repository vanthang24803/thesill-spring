package com.example.api.common.mappers.impl;

import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.auth.UserResponse;
import com.example.api.domain.entities.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<AuthEntity, UserResponse> {

    private final ModelMapper modelMapper;

    @Override
    public UserResponse mapTo(AuthEntity authEntity) {
        return modelMapper.map(authEntity, UserResponse.class);
    }

    @Override
    public AuthEntity mapFrom(UserResponse userResponse) {
        return modelMapper.map(userResponse, AuthEntity.class);
    }
}
