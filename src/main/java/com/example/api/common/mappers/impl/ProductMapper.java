package com.example.api.common.mappers.impl;


import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.product.ProductResponse;
import com.example.api.domain.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements Mapper<ProductEntity, ProductResponse> {
    private final ModelMapper modelMapper;

    @Override
    public ProductResponse mapTo(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductResponse.class);
    }

    @Override
    public ProductEntity mapFrom(ProductResponse productResponse) {
        return modelMapper.map(productResponse, ProductEntity.class);
    }
}
