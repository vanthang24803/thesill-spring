
package com.example.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "photos")
@Data
public class PhotoEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "photo_url", columnDefinition = "TEXT", nullable = false)
    private String url;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
