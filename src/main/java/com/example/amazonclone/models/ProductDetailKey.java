package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Table(name= "product_detail_keys")
public class ProductDetailKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name= "key", length = 64)
    private String key;

    @ManyToOne
    @JoinColumn(name="product_type_id")
    private ProductType productType;

    @OneToOne(mappedBy = "productDetailKey")
    private ProductDetailValue productDetailValue;
}
