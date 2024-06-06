package com.example.api.services;

import com.example.api.domain.dtos.category.CategoryRequest;
import com.example.api.domain.dtos.category.CategoryResponse;
import com.example.api.common.helpers.Response;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Response<CategoryResponse> save(CategoryRequest request);

   void createSeed();

    Response<List<CategoryResponse>> findAll();

    Response<CategoryResponse> findOne(UUID id);

    Response<CategoryResponse> update(UUID id, CategoryRequest request);

    void delete(UUID id);
}
