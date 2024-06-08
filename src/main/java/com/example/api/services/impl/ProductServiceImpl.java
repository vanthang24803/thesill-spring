package com.example.api.services.impl;

import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.helpers.ProductQuery;
import com.example.api.common.mappers.Mapper;
import com.example.api.common.helpers.Response;
import com.example.api.common.messages.NotFoundMessage;
import com.example.api.domain.dtos.product.*;
import com.example.api.domain.entities.CategoryEntity;
import com.example.api.domain.entities.ProductEntity;
import com.example.api.repositories.CategoryRepository;
import com.example.api.repositories.ProductRepository;
import com.example.api.repositories.TagRepository;
import com.example.api.services.ProductService;
import com.example.api.services.UploadService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UploadService uploadService;
    private final Mapper<ProductEntity, ProductResponse> mapper;
    private final Mapper<ProductEntity, ProductDetailResponse> mapperDetail;

    @Override
    public Response<ProductResponse> save(CreateProductRequest request, MultipartFile file) {

        var tagName = tagRepository.findByName(request.getTag()).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.TAG_NOT_FOUND)
        );

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
                .tag(tagName)
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

    @Override
    public ProductFilterResponse findAll(ProductQuery query) {
        int page = query.getPage();
        int limit = query.getLimit();
        String order = query.getOrder();

        var products = this.pagination(page, limit, order);

        List<ProductResponse> response = products
                .getContent()
                .stream()
                .map(mapper::mapTo)
                .toList();

        return ProductFilterResponse.builder()
                .limit(limit)
                .page(page)
                .totalPage(products.getTotalPages())
                .totalProduct((int) products.getTotalElements())
                .result(response)
                .build();
    }

    @Override
    public Response<ProductResponse> update(String id, UpdateProductRequest update) {
        var product = this.findProductThrowException(id);

        product.setName(update.getName());
        product.setDescription(update.getDescription());
        product.setGuide(update.getGuide());
        product.setPublished(update.getPublished());

        productRepository.save(product);

        var result = mapper.mapTo(product);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public Response<ProductDetailResponse> findOne(String id) {
        var product = this.findProductThrowException(id);

        var result = mapperDetail.mapTo(product);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public SearchResponse search(String name) {

        var products = productRepository.findByNameContainingIgnoreCase(name);

        List<ProductResponse> response = products
                .stream()
                .map(mapper::mapTo)
                .toList();

        return SearchResponse
                .builder()
                .total(products.size())
                .result(response)
                .build();
    }

    @Override
    public void remove(String id) {
        var product = this.findProductThrowException(id);

        uploadService.delete(product.getId());
        productRepository.delete(product);
    }

    private Page<ProductEntity> pagination(int page, int limit, String order) {
        Page<ProductEntity> products;
        if ("asc".equalsIgnoreCase(order)) {
            products = productRepository.findAll(PageRequest.of(page - 1, limit, Sort.by("createdAt").descending()));
        } else {
            products = productRepository.findAll(PageRequest.of(page - 1, limit));
        }

        return products;
    }

    private ProductEntity findProductThrowException(String id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND)
        );
    }

}
