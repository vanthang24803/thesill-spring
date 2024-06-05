package com.example.api.common.configs;

import com.example.api.services.CategoryService;
import com.example.api.services.RoleService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    ApplicationRunner applicationRunner(RoleService roleService, CategoryService categoryService) {
        return args -> {
            roleService.createSeedRole();
            categoryService.createSeed();
        };
    }


}
