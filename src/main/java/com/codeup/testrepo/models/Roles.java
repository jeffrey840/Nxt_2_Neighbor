package com.codeup.testrepo.models;
import jakarta.persistence.*;


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

    public Long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getRole() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
