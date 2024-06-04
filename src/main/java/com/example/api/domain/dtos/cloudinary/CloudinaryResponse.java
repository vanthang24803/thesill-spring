package com.example.api.domain.dtos.cloudinary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloudinaryResponse {
    private String url;

    @JsonProperty("public_id")
    private String publicId;
}
