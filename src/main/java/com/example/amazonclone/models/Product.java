package com.example.amazonclone.models;

import jakarta.persistence.*;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private double price;

    @Column(name="description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    @OneToMany(mappedBy = "product")
    private Collection<ProductDetailValue> productDetailValues = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Collection<ProductColor> productColors = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Collection<ProductReview> productReviews = new ArrayList<>();


}
