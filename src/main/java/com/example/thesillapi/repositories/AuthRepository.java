package com.example.thesillapi.repositories;


import com.example.thesillapi.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<Auth, UUID> {

    Optional<Auth> findByEmail(String email);
}
