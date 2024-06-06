package com.example.api.services;

import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.dtos.product.CreateProductRequest;
import com.example.api.domain.dtos.product.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Response<ProductResponse> save(CreateProductRequest request,  MultipartFile file);

}
