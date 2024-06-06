package com.example.api.services.impl;

import com.example.api.common.mappers.Mapper;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.product.CreateProductRequest;
import com.example.api.domain.dtos.product.ProductResponse;
import com.example.api.domain.entities.CategoryEntity;
import com.example.api.domain.entities.ProductEntity;
import com.example.api.repositories.CategoryRepository;
import com.example.api.repositories.ProductRepository;
import com.example.api.services.ProductService;
import com.example.api.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UploadService uploadService;
    private final Mapper<ProductEntity, ProductResponse> mapper;

    @Override
    public Response<ProductResponse> save(CreateProductRequest request, MultipartFile file) {

        List<CategoryEntity> categories = new ArrayList<>();

        for (var category : request.getCategories()) {
            var item = categoryRepository.findByName(category.getName());
            item.ifPresent(categories::add);
        }

        var product = ProductEntity
                .builder()
                .name(request.getName())
                .categories(categories)
                .createdAt(LocalDateTime.now())
                .published(false)
                .build();

        try {
            var upload = uploadService.upload(file);
            product.setThumbnail(upload.getUrl());
            product.setId(upload.getPublicId());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        productRepository.save(product);

        var result = mapper.mapTo(product);

        return new Response<>(HttpStatus.CREATED.value(), result);
    }
}
