package com.example.api.repositories;

import com.example.api.domain.entities.BillboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  BillboardRepository extends JpaRepository<BillboardEntity, String> {
    Optional<BillboardEntity> findById(String id);
}
