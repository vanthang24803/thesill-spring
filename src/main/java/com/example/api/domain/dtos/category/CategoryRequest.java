package com.example.api.domain.dtos.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    @NotEmpty(message = "Category name is required")
    @Length(min = 1, max = 255, message = "Category name must be between 1 and 255 characters")
    private String name;
}
