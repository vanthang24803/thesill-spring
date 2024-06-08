package com.example.api.domain.dtos.tag;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {
    @NotEmpty(message = "Name is required")
    @Length(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;
}
