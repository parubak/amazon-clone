package com.example.amazonclone.models;

import jakarta.persistence.*;
import jakarta.annotation.Nullable;
import lombok.Data;

@Entity
@Data
@Table(name="product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="image")
    private String image;

    @ManyToOne()
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;
}
