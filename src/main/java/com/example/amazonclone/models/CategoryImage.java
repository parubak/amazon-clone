package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;


@Data
@Entity
@Table(name="category_images")
public class CategoryImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="image")
    @Lob
    private Blob image;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
