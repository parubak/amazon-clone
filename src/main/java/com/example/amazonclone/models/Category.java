package com.example.amazonclone.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name="categories")
public class Category implements ModelEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Nullable
    @OneToMany(mappedBy = "category")
    private Set<Subcategory> subcategory;

    @OneToOne(mappedBy = "category")
    private CategoryImage image;

    public Category() {

    }
}
