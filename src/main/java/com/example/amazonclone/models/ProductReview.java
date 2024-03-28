package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_reviews")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="mark", nullable = false)
    private Double mark;

    @Column(name="username", length = 64, nullable = false)
    private String username;

    @Column(name="review_text", nullable = true)
    private String reviewText;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
