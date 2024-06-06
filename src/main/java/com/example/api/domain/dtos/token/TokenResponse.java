package com.example.api.domain.dtos.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {

    @JsonProperty("refresh_token")
    public String refreshToken;

    @JsonProperty("access_token")
    public String accessToken;
}
