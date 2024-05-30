package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="date")
    Date createDate;
    @Column(name="uname")
    String userName;
    @Column(name="color")
    String color;
    @Column(name="size")
    String size;
    @Column(name = "text",length = 500)
    String text;

    @Column(name="rating")
    private double rating;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private Collection<CommentImage> commentImages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
