package com.example.api.domain.dtos.color;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorRequest {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Value is required")
    private String value;
    private Long quantity;
}
