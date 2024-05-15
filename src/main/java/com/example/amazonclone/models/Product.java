package com.example.amazonclone.models;

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
@Table(name="products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @ManyToOne()
    private Seller seller;

    @Column(name="rating")
    private Double rating;

    @Column(name="mark")
     private Integer  mark;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Collection<Comment> comments= new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Collection<ProductItem> productItems= new ArrayList<>();;

    @ManyToOne
    private  Subgroup subgroup;
}
