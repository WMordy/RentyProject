package com.example.rentyproject.Model;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String password ;
    private String token ;
    private String phone ;
    private String email ;
    private ArrayList<Post> postArrayList= new ArrayList() ;

    public User(String firstName, String password){
        this.firstName = firstName;
        this.password = password ;
    }
    public User(String firstName,String lastName, String password, String phone, String email){
        this.firstName = firstName;
        this.password = password ;
        this.lastName = lastName ;
        this.phone = phone ;
        this.email = email ;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public void setPostArrayList(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    public String getFirstName() {
        return firstName;
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

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}
