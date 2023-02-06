package com.codeup.testrepo.models;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "listings")
public class Listings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private Long location;
    @Column(unique = true, length = 50, nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String body;

    @Column(length = 100)
    private Long price;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "listings_categories",
            joinColumns ={@JoinColumn(name="category_id")}
    )
    private List<Categories> categories;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return body;
    }

    public void setDescription(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}