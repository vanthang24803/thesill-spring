package com.example.api.common.mappers.impl;

import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.color.ColorResponse;
import com.example.api.domain.entities.ColorEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColorMapper implements Mapper<ColorEntity , ColorResponse> {
    private final ModelMapper modelMapper;
    @Override
    public ColorResponse mapTo(ColorEntity colorEntity) {
        return modelMapper.map(colorEntity, ColorResponse.class);
    }

    @Override
    public ColorEntity mapFrom(ColorResponse colorResponse) {
        return modelMapper.map(colorResponse, ColorEntity.class);
    }
}
