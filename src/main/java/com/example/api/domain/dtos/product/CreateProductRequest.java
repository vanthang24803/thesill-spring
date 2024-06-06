package com.example.api.domain.dtos.product;

import com.example.api.domain.dtos.category.CategoryRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {
    @NotEmpty(message = "Name is required")
    @Length(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;
    private List<CategoryRequest> categories = new ArrayList<>();
}


