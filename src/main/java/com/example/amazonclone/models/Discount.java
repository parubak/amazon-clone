package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", length = 64)
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="period")
    private Timestamp period;

    @OneToOne
    @JoinColumn(name = "product_color_id")
    private ProductColor productColor;
}
