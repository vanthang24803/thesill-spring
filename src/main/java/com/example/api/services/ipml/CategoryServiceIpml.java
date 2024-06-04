package com.example.api.services.ipml;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceIpml implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final Mapper<CategoryEntity, CategoryResponse> mapper;

    @Override
    public Response<CategoryResponse> save(CategoryRequest request) {
        CategoryEntity category = new CategoryEntity();

        category.setName(request.getName());

        categoryRepository.save(category);

        var result = mapper.mapTo(category);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public Response<List<CategoryResponse>> createSeed() {

        ArrayList<CategoryResponse> categories = new ArrayList<>();

        Set<String> seeds = seedCategory();

        for (String seed : seeds) {
            CategoryEntity category = new CategoryEntity();
            category.setName(seed);
            categoryRepository.save(category);
            var response = mapper.mapTo(category);
            categories.add(response);
        }
        return new Response<>(HttpStatus.OK.value(), categories);
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
                () -> new NotFoundException("Category not found!")
        );

        var result = mapper.mapTo(category);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public Response<CategoryResponse> update(UUID id, CategoryRequest request) {
        var category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category not found!")
        );

        category.setName(request.getName());

        categoryRepository.save(category);

        var result = mapper.mapTo(category);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public void delete(UUID id) {
        var category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category not found!")
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
