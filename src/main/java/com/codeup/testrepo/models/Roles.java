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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Roles")
//    private List<User> user;

    public Roles() {
    }

    public Roles(long id, String user_role){
        this.id = id;
        this.user_role = user_role;
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

}
