package com.example.api.controllers;

import com.example.api.common.helpers.ProductQuery;
import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.product.*;
import com.example.api.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Endpoints for product manager")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Response<ProductResponse>> create(
            @ModelAttribute @Valid CreateProductRequest request,
            @RequestParam("thumbnail") MultipartFile file
    ) {
        return new ResponseEntity<>(productService.save(request, file), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ProductFilterResponse> findAll(
            @ModelAttribute ProductQuery query
    ) {
        return ResponseEntity.ok(productService.findAll(query));
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<ProductDetailResponse>> findOne(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(productService.findOne(id));
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> search(
            @RequestParam("q") String name
    ) {
        return ResponseEntity.ok(productService.search(name));
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<ProductResponse>> update(
            @PathVariable("id") String id,
            @RequestBody @Valid UpdateProductRequest request) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remove(
            @PathVariable("id") String id
    ) {
        productService.remove(id);
        return ResponseEntity.ok().build();
    }
}
