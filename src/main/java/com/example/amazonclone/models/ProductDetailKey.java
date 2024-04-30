package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name= "product_detail_keys")
public class ProductDetailKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name= "`key`", length = 64)
    private String key;

    @ManyToOne
    @JoinColumn(name="product_type_id")
    private ProductType productType;

    @OneToMany(mappedBy = "productDetailKey")
    private List<ProductDetailValue> productDetailValues = new ArrayList<>();
}
