package com.example.api.domain.dtos.option;

import com.example.api.domain.dtos.color.ColorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Long quantity;
    private Integer sale;
    private List<ColorResponse> colors = new ArrayList<>();
    private LocalDateTime createdAt;
}
