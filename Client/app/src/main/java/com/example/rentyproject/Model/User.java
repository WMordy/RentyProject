package com.example.rentyproject.Model;

import java.util.ArrayList;

public class User {
    private String username ;
    private String password ;
    private String token ;
    private String phone ;
    private String email ;
    private ArrayList<Post> postArrayList= new ArrayList() ;

    public User(String username, String password){
        this.username = username ;
        this.password = password ;
    }
    public User(String username, String password, String phone, String email){
        this.username = username ;
        this.password = password ;
        this.phone = phone ;
        this.email = email ;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public void setPostArrayList(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Post> getPostArrayList() {
        return postArrayList;
    }
}
