package com.example.api.common.mappers.impl;

import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.option.HandlerOptionResponse;
import com.example.api.domain.entities.OptionEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HandlerOptionMapper implements Mapper<OptionEntity, HandlerOptionResponse> {

    private final ModelMapper modelMapper;

    @Override
    public HandlerOptionResponse mapTo(OptionEntity optionEntity) {
        return modelMapper.map(optionEntity, HandlerOptionResponse.class);
    }

    @Override
    public OptionEntity mapFrom(HandlerOptionResponse handlerOptionResponse) {
        return modelMapper.map(handlerOptionResponse, OptionEntity.class);
    }
}
