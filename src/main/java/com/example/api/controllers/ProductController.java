package com.example.api.controllers;

import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.product.CreateProductRequest;
import com.example.api.domain.dtos.product.ProductResponse;
import com.example.api.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Response<ProductResponse>> create(
            @ModelAttribute @Valid CreateProductRequest request,
            @RequestParam("thumbnail") MultipartFile file
    ) {
        return new ResponseEntity<>(productService.save(request, file), HttpStatus.CREATED);
    }
}
