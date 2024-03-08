package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name="discounts")
public class Discount implements ModelEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="price")
    private double price;

    @Column(name="period")
    private Timestamp period;

    @OneToOne(mappedBy = "discount")
    private Product product;

    public Discount() {

    }
}
