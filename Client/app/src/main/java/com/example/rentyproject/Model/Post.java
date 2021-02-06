package com.example.rentyproject.Model;

public class Post {
    private  String category ;
    private String field ;
    private int price ;
    private int id ;    // TODO to implement later
    private String location ;
    private  String description ;
    private String imageLink ;

    public Post(String category, String field, int price,  String location, String description, String imageLink) {
        this.category = category;
        this.field = field;
        this.price = price;
        this.location = location;
        this.description = description;
        this.imageLink = imageLink;
    }

    public boolean deletePost(int id ){
        boolean isDeleted = true ;
        return isDeleted ;
    }

}
