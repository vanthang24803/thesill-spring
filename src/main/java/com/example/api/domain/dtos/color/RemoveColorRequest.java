package com.example.api.domain.dtos.color;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemoveColorRequest {
    @NotNull
    List<ColorIdRequest> colors;
}


