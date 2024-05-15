package com.example.amazonclone.models;

import jakarta.persistence.*;
import jakarta.annotation.Nullable;
import lombok.Data;

@Entity
@Data
@Table(name="comment_images")
public class CommentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="image")
    private String image;

    @ManyToOne()
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
