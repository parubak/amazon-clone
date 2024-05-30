package com.example.amazonclone.models;

import jakarta.persistence.*;
import jakarta.annotation.Nullable;
import lombok.Data;

@Entity
@Data
@Table()
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="image")
    private String image;

    @ManyToOne()
    @JoinColumn(name = "productitem_id")
    private ProductItem productItem;
}
