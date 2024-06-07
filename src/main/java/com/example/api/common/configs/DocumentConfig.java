package com.example.api.common.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class DocumentConfig {
    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().info(new Info().title("Thesill API").version("1.0.0"));
    }
}
