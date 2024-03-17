package com.example.amazonclone.models;

import com.example.amazonclone.repos.CategoryRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="subcategory_id")
    private Subcategory subcategory;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    @Nullable
    private Discount discount;

    @OneToOne
    @JoinColumn(name="main_image_id")
    @Nullable
    private ProductImage mainImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @Nullable
    private Collection<ProductImage> productImages = new ArrayList<>();

}
