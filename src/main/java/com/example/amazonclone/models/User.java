package com.example.amazonclone.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    protected String email;
    @Column(nullable = false)
    protected String password;

    @Column(nullable = true)
    protected String firstName;

    @Column(nullable = true)
    protected String lastName;

    @Column(nullable = true, length = 64)
    protected String number;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Collection<Order> orders= new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    protected List<Role> roles = new ArrayList<>();


    public  User(String name,String lastName, String email, String encode, List<Role> asList) {
        this.firstName =name;
        this.lastName=lastName;
        this.email=email;
        this.password=encode;
        this.roles=asList;
    }
}