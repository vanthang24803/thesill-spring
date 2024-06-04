package com.example.api.controllers;

import com.example.api.domain.dtos.category.CategoryRequest;
import com.example.api.domain.dtos.category.CategoryResponse;
import com.example.api.domain.dtos.message.Response;
import com.example.api.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("seeds")
    public ResponseEntity<Response<List<CategoryResponse>>> seeds() {
        return ResponseEntity.ok(categoryService.createSeed());
    }

    @PostMapping()
    public ResponseEntity<Response<CategoryResponse>> create(
            @RequestBody @Valid CategoryRequest request
    ) {
        return new ResponseEntity<>(categoryService.save(request), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Response<List<CategoryResponse>>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(categoryService.findOne(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id,
                                    @RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }


}
