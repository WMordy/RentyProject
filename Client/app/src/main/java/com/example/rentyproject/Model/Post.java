package com.example.rentyproject.Model;

public class Post {
    private  String category ;
    private String field ;
    private String price ;
    private int id ;    // TODO to implement later
    private String userId;
    private String location ;
    private  String description ;
    private String imageLink ;

    public String getField() {
        return field;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }

    public Post(String category, String field, int price, String location, String description, String imageLink) {
        this.category = category;
        this.field = field;
        //this.price = price.toString();
        this.location = location;
        this.description = description;
        this.imageLink = imageLink;
    }
    public Post(String userId,String field , String price , String city , String description){
        this.field = field;
        this.price = price;
        this.location = city;
        this.description = description;
        this.userId = userId;
    }

    public boolean deletePost(int id ){
        boolean isDeleted = true ;
        return isDeleted ;
    }

}
