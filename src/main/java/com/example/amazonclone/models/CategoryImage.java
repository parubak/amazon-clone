package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name="category_images")
public class CategoryImage implements ModelEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="image")
    @Lob
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryImage() {

    }
}
