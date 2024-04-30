package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="colors")
@Data
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="color")
    private String color;

    @OneToMany(mappedBy = "color")
    private List<ProductColor> productColors = new ArrayList<>();
}
