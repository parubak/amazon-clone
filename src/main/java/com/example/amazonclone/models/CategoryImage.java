package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Blob;


@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name="category_images")
public class CategoryImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="image")
    @Lob
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryImage() {

    }
}
