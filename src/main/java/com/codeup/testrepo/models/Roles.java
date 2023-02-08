package com.codeup.testrepo.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Entity
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String user_role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private List<User> user;

    public Roles() {
    }

    public Roles(long id, String user_role) {
        this.id = id;
        this.user_role = user_role;
    }

    public Roles(long id, String user_role, List<User> user) {
        this.id = id;
        this.user_role = user_role;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

}
