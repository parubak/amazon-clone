package com.example.amazonclone.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Table(name="product_colors")
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="color", length = 64)
    private String color;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(mappedBy = "productColor", cascade = CascadeType.REMOVE)
    @Nullable
    private Discount discount;

    @OneToOne
    @JoinColumn(name="main_image_id", nullable = true)
    @Nullable
    private ProductColorImage mainImage;

    @OneToMany(mappedBy = "productColor", cascade = CascadeType.REMOVE)
    @Nullable
    private Collection<ProductColorImage> productColorImages = new ArrayList<>();

    @ManyToMany(mappedBy = "productColors")
    private Collection<ProductSize> productSizes = new ArrayList<>();
}
