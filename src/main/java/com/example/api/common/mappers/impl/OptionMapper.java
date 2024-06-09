package com.example.api.common.mappers.impl;

import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.option.OptionResponse;
import com.example.api.domain.entities.OptionEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptionMapper implements Mapper<OptionEntity, OptionResponse> {

    private final ModelMapper modelMapper;

    @Override
    public OptionResponse mapTo(OptionEntity optionEntity) {
        return modelMapper.map(optionEntity, OptionResponse.class);
    }

    @Override
    public OptionEntity mapFrom(OptionResponse optionResponse) {
        return modelMapper.map(optionResponse, OptionEntity.class);
    }
}
