package com.example.smartdroid.Model;

public class User {
    String id, name, email, point;

    public User(){

    }
    public User(String id, String name, String email, String point) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
