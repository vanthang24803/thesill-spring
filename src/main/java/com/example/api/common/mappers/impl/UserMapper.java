package com.example.api.common.mappers.impl;

import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.auth.UserDto;
import com.example.api.domain.entities.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<AuthEntity, UserDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto mapTo(AuthEntity authEntity) {
        return modelMapper.map(authEntity, UserDto.class);
    }

    @Override
    public AuthEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, AuthEntity.class);
    }
}
