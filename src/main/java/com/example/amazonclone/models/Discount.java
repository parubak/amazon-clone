//"package com.example.amazonclone.models;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.sql.Timestamp;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "discounts")
//public class Discount {
//    //Delete class
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private Long id;
//
//    @Column(name = "price")
//    private double price;
//
//    @Column(name = "discount")
//    private Integer discount;
//
//    @Column(name = "period")
//    private Timestamp period;
//
//
//    @OneToOne()
//    private ProductItem productItem;
//}
