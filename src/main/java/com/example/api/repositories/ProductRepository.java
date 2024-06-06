package com.example.api.repositories;

import com.example.api.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity , String> {
    Optional<ProductEntity> findById(String id);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
