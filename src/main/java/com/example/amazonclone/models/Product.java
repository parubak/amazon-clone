package com.example.amazonclone.models;

import com.example.amazonclone.repos.CategoryRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name="products")
public class Product implements ModelEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @ManyToOne
    @JoinColumn(name="subcategory_id")
    private Subcategory subcategory;

    @OneToOne
    @JoinColumn(name="discount_id")
    private Discount discount;

    @OneToOne
    @JoinColumn(name="main_image_id")
    @Nullable
    private ProductImage mainImage;

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> productImages;

    public Product() {

    }
}
