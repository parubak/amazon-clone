package com.example.amazonclone.models;

import com.example.amazonclone.repos.CategoryRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
    private String price;

    @ManyToOne
    @JoinColumn(name="subcategory_id")
    private Subcategory subcategory;

    @OneToOne
    @JoinColumn(name="discount_id")
    private Discount discount;

    @OneToOne
    @JoinColumn(name="main_image_id")
    private ProductImage mainImage;

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> productImages;

}
