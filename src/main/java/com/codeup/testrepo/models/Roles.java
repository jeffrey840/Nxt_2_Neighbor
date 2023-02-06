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

    @OneToOne
    private User users;
}
