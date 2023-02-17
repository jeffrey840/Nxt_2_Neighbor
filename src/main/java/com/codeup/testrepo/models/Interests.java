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

//    @Column(nullable = false, length = 100)
//    private String categories;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Categories categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

//    public String getCategories() {
//        return categories;
//    }
//
//    public void setCategories(String categories) {
//        this.categories = categories;
//    }
}
