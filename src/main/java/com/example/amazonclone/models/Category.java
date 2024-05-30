//package com.example.amazonclone.models;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.annotation.Nullable;
//import jakarta.persistence.*;
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name="categories")
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private Long id;
//
//    @Column(name="category",nullable=false)
//    private String name;
//
//    @OneToMany(mappedBy="category")
//    private Collection<Identity> identities= new ArrayList<>();
//}
