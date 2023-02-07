package com.codeup.testrepo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Listings> listing;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private List<Interests> interests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Listings> getListing() {
        return listing;
    }

    public void setListing(List<Listings> listing) {
        this.listing = listing;
    }

    public User getUser(){
        return user;
    }

    public List<Interests> getInterests() {
        return interests;
    }

    public void setInterests(List<Interests> interests) {
        this.interests = interests;
    }
}

