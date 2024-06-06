package com.example.api.repositories;

import com.example.api.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<ProductEntity , String> {
}
