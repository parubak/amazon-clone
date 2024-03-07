package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Subcategory> subcategory;

    @OneToOne(mappedBy = "category")
    private CategoryImage image;
}
