package com.example.api.domain.dtos.color;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorIdRequest {
    @NotNull(message = "Id is required")
    private UUID id;
}
