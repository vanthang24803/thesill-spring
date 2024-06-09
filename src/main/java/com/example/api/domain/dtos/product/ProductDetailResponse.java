package com.example.api.domain.dtos.product;

import com.example.api.domain.dtos.category.CategoryResponse;
import com.example.api.domain.dtos.option.OptionResponse;
import com.example.api.domain.dtos.photo.PhotoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailResponse {
    private String id;
    private String name;
    private String thumbnail;
    private String description;
    private String guide;
    private Boolean published;
    private List<CategoryResponse> categories = new ArrayList<>();
    private List<PhotoResponse> photos = new ArrayList<>();
    private List<OptionResponse> options = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
