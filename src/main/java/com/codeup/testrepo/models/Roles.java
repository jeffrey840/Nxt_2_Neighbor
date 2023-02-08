package com.codeup.testrepo.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name="roles")
public class Roles {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String user_role;

    public Roles(long id,String user_role) {
        this.id = id;
        this.user_role = user_role;

    }
    public Roles() {

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUser_role() {
        return id;
    }
    public void setUser_role(Long id) {
        this.id = id;
    }

}
