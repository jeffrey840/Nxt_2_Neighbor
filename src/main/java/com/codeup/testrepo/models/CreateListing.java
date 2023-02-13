package com.codeup.testrepo.models;

public class CreateListing {
    private String title;
    private String bio;
    private String image;

    public String getBio() {
        return bio;
    }

    public void setBio(){
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String address) {
        this.title = address;
    }
}
