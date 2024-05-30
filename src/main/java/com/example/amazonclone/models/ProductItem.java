package com.example.amazonclone.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table()
public class ProductItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="image")
    private String image;

    @Column(name="color")
    private String color;

    @Column(name="price")
    private Double price;


    @Nullable
    private Double discount;

    @OneToMany( cascade = CascadeType.REMOVE)
    private Collection<ProductImage> productImages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Size> sizes = new ArrayList<>();


    @OneToMany()
    private Collection<ProductDetails> productDetails = new ArrayList<>();

    @OneToOne()
    Order order;
}
