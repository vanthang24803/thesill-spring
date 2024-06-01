package com.example.thesillapi.repositories;


import com.example.thesillapi.domain.entities.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<AuthEntity, UUID> {

    Optional<AuthEntity> findByEmail(String email);
}
