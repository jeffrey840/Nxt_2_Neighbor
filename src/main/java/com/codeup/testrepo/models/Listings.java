package com.codeup.testrepo.models;
import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name = "listings")
public class Listings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column(length = 200)
    private String address;
    @Lob
    @Column(unique = true, length = 50, nullable = false)
    private String title;
    @Lob
    @Column(nullable = false, length = 1000)
    private String description;
    @Lob
    @Column(length = 100)
    private double price;

//    @Column(length = 50)
//    private String img;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "listings_categories",
            joinColumns ={@JoinColumn(name="category_id")}
    )
    private List<Categories> categories;

    public Listings(String title, String address, String description, double price){
        this.title = title;
        this.address = address;
        this.description = description;
        this.price = price;
    }

    public Listings() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

}
