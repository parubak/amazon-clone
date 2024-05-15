package com.example.amazonclone.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product_items")
public class ProductItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="image")
    private String image;

    @Column(name="color")
    private String color;

    @Column(name="price")
    private Double price;

    @OneToOne()
    @Nullable
    private Discount discount;

    @OneToMany( cascade = CascadeType.REMOVE)
    private Collection<ProductImage> productImages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Size> sizes = new ArrayList<>();


    @OneToMany(mappedBy = "productItem")
    private Collection<ProductDetails> productDetails = new ArrayList<>();

    @ManyToMany(mappedBy = "productItems")
    private Collection<Order> orders=new ArrayList<>();
}
