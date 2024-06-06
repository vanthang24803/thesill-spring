package com.example.api.common.mappers.impl;

import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.product.ProductDetailResponse;
import com.example.api.domain.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDetailMapper implements Mapper<ProductEntity, ProductDetailResponse> {
    private final ModelMapper modelMapper;

    @Override
    public ProductDetailResponse mapTo(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDetailResponse.class);
    }

    @Override
    public ProductEntity mapFrom(ProductDetailResponse productDetailResponse) {
        return modelMapper.map(productDetailResponse, ProductEntity.class);
    }
}
