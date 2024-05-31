package com.example.thesillapi.entities;

import com.example.thesillapi.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 255, nullable = false)
    private RoleEnum name;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
