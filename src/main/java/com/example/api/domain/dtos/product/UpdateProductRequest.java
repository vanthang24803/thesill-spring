package com.example.api.domain.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequest {
    @NotEmpty(message = "Name is required")
    @Length(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    private Boolean published;

    @NotEmpty()
    @NotBlank
    private String description;

    @NotEmpty()
    @NotBlank
    private String guide;

}
