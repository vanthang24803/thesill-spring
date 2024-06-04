package com.example.api.domain.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private String avatar;

    private Boolean verify;

    List<RoleDto> roles;
}
