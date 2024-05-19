package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="quantity")
    Integer quantity;

    @Column(name="status")
    String status;

    @Column(name="size")
    String size;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_item_id", referencedColumnName = "id")
    private ProductItem productItem;



}
