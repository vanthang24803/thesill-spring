package com.example.api.repositories;

import com.example.api.domain.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {
    Optional<TagEntity> findById(UUID id);

    Optional<TagEntity> findByName(String name);

   boolean existsByName(String name);
}
