package com.example.api.domain.dtos.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {
    @JsonProperty("refresh_token")
    @NotBlank(message = "Token not blank")
    @NotEmpty(message = "Token not empty")
    public String refreshToken;
}
