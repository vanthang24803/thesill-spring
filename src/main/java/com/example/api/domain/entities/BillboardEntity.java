package com.example.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "billboards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillboardEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "billboard_url", columnDefinition = "TEXT")
    private String url;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
