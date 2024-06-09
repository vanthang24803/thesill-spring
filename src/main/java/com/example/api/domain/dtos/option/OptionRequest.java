package com.example.api.domain.dtos.option;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionRequest {

    @NotBlank()
    @NotEmpty()
    private String name;

    private BigDecimal price;

    private Long quantity;

    private Integer sale;
}
