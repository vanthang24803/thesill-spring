package com.example.api.repositories;

import com.example.api.domain.entities.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<PhotoEntity, String> {
    List<PhotoEntity> findAllByProductId(String productId);

    Optional<PhotoEntity> findById(String id);
}
