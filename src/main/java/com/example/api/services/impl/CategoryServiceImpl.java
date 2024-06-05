package com.example.api.services.impl;

import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.category.CategoryRequest;
import com.example.api.domain.dtos.category.CategoryResponse;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.entities.CategoryEntity;
import com.example.api.repositories.CategoryRepository;
import com.example.api.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final Mapper<CategoryEntity, CategoryResponse> mapper;
    private static final String CATEGORY_NOT_FOUND = "Category not found!";

    @Override
    public Response<CategoryResponse> save(CategoryRequest request) {
        CategoryEntity category = new CategoryEntity();

        category.setName(request.getName());

        categoryRepository.save(category);

        var result = mapper.mapTo(category);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public void createSeed() {
        Set<String> seeds = seedCategory();

        for (String seed : seeds) {
            if (categoryRepository.findByName(seed).isEmpty()) {
                CategoryEntity category = new CategoryEntity();
                category.setName(seed);
                categoryRepository.save(category);
            }
        }
    }

    @Override
    public Response<List<CategoryResponse>> findAll() {
        var categories = categoryRepository.findAll();

        var result = categories.stream().map(mapper::mapTo)
                .toList();

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public Response<CategoryResponse> findOne(UUID id) {
        var category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(CATEGORY_NOT_FOUND)
        );

        var result = mapper.mapTo(category);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public Response<CategoryResponse> update(UUID id, CategoryRequest request) {
        var category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(CATEGORY_NOT_FOUND)
        );

        category.setName(request.getName());

        categoryRepository.save(category);

        var result = mapper.mapTo(category);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public void delete(UUID id) {
        var category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(CATEGORY_NOT_FOUND)
        );
        categoryRepository.delete(category);
    }

    private Set<String> seedCategory() {
        return Set.copyOf(Set.of(
                "Tree",
                "Plant",
                "Planter",
                "Plant Care",
                "Garden & Patio",
                "Gifts",
                "Faux",
                "Orchids",
                "Blooms"
        ));
    }
}
