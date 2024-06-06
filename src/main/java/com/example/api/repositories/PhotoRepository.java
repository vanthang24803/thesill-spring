package com.example.api.repositories;

import com.example.api.domain.entities.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoEntity , String> {

}
