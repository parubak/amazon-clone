package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Table(name="product_sizes")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="size", nullable = false, length = 10)
    private String size;

    @ManyToMany(mappedBy = "productSizes")
    private Collection<ProductColor> productColors = new ArrayList<>();
}
