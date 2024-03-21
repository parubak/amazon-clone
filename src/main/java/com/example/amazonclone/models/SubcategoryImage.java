package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "subcategory_images")
public class SubcategoryImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="image")
    @Lob
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
}
