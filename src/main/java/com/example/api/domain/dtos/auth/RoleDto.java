package com.example.api.domain.dtos.auth;

import com.example.api.domain.enums.RoleEnum;
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
public class RoleDto {
    private UUID id;

    private RoleEnum name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
