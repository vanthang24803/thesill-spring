package com.example.api.services;

import com.example.api.common.helpers.ProductQuery;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.product.*;
import org.springframework.web.multipart.MultipartFile;


public interface ProductService {
    Response<ProductResponse> save(CreateProductRequest request,  MultipartFile file);

    ProductFilterResponse findAll(ProductQuery query);

    Response<ProductResponse> update(String id, UpdateProductRequest update);

    Response<ProductResponse> findOne(String id);

    SearchResponse search(String name);

    void remove(String id);
}
