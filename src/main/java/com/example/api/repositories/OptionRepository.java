package com.example.api.repositories;

import com.example.api.domain.entities.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OptionRepository extends JpaRepository<OptionEntity, UUID> {
    List<OptionEntity> findAllByProductId(String productId);
}
