package com.codeup.testrepo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categories")
public class Categories {

    @Id // Specifies that the following field is the primary key for this entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the strategy for generating primary key values.
    @Column(nullable = false) // Specifies that the following field maps to a column in the database table, and it cannot be null.
    private long id;

    @Column(nullable = false, length = 50) // Specifies that the following field maps to a column in the database table, and it cannot be null. It also sets the maximum length for the column.
    private String name;

    @ManyToMany(mappedBy = "categories") // Specifies a many-to-many relationship with the "Listings" entity,
    // and the "categories" field in the "Listings" entity is the owning side.
    private List<Listings> listing;

    @ManyToOne // Specifies a many-to-one relationship with the "User" entity.
    @JoinColumn (name = "user_id") // Specifies the foreign key column name that references the primary key of the "User" entity.
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categories") // Specifies a one-to-many relationship with the "Interests" entity, and the "categories" field in the "Interests" entity is the owning side.
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

