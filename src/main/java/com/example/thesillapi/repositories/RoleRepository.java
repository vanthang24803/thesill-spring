package com.example.thesillapi.repositories;

import com.example.thesillapi.domain.entities.RoleEntity;
import com.example.thesillapi.domain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
