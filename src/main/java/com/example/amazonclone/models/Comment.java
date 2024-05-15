package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    Date createDate;

    String userName;

    String color;

    String size;
    @Column(name = "text",length = 500)
    String text;

    @Column(name="rating",nullable=false)
    private double rating;

    @OneToMany( cascade = CascadeType.REMOVE)
    private Collection<CommentImage> commentImages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
