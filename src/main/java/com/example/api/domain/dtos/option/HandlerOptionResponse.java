package com.example.api.domain.dtos.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HandlerOptionResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Long quantity;
    private Integer sale;
    private LocalDateTime createdAt;
}
