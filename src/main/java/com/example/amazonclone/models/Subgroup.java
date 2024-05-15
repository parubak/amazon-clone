package com.example.amazonclone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="subgroups")
public class Subgroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="subgroup",nullable=false)
    private String name;

    @OneToMany(mappedBy="subgroup")
    private Collection<Product> products = new ArrayList<>();

    @OneToMany(mappedBy="subgroup")
    private Collection<SubInfo> subInfos= new ArrayList<>();


}
