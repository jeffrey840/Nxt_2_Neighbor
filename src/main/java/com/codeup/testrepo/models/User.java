package com.codeup.testrepo.models;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(unique = true, length = 50)
    private String email;

    @Column(length = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "roles")
    private Roles role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Listings> homeListings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Categories> categories;



    public User() {
    }

//    only sets the authentication things we need from the user
    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
//        role = copy.role;
    }

    public User(long id, String username, String email, String password, Roles role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }


    public List<Listings> getListings() {
        return homeListings;
    }

    public void setPosts(List<Listings> listings) {
        this.homeListings = homeListings;
    }
}
