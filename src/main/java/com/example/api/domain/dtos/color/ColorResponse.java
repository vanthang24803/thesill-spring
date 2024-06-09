package com.example.api.domain.dtos.color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorResponse {
    private UUID id;
    private String name;
    private String value;
    private LocalDateTime createdAt;
}
