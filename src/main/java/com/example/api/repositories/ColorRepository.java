package com.example.api.repositories;

import com.example.api.domain.entities.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColorRepository extends JpaRepository<ColorEntity, UUID> {
}
