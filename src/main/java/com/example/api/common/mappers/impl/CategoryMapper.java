package com.example.api.common.mappers.impl;

import com.example.api.domain.dtos.category.CategoryResponse;
import com.example.api.domain.entities.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.example.api.common.mappers.Mapper;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements Mapper<CategoryEntity, CategoryResponse> {
    private final ModelMapper mapper;

    @Override
    public CategoryResponse mapTo(CategoryEntity categoryEntity) {
        return mapper.map(categoryEntity, CategoryResponse.class);
    }

    @Override
    public CategoryEntity mapFrom(CategoryResponse categoryResponse) {
        return mapper.map(categoryResponse, CategoryEntity.class);
    }
}
