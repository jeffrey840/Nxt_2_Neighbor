package com.codeup.testrepo.models;

import jakarta.persistence.*;

@Entity
@Table (name = "interests")
public class Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Categories categories;
}
