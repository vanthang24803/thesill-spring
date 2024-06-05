package com.example.api.repositories;

import com.example.api.domain.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity , UUID> {
    Optional<CategoryEntity> findById(UUID id);

    Optional<CategoryEntity> findByName(String name);
}

