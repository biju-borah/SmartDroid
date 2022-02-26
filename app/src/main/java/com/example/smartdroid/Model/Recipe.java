package com.example.smartdroid.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    String name, imgUrl, shortDesc, longDesc, author, videoUrl;
    ArrayList<String> rating, tag, ingredients;

    public  Recipe(){

    }

    public Recipe(String name,  ArrayList<String> tag) {
        this.name = name;
//        this.imgUrl = imgUrl;
//        this.shortDesc = shortDesc;
//        this.longDesc = longDesc;
//        this.author = author;
//        this.videoUrl = videoUrl;
//        this.rating = rating;
        this.tag = tag;
//        this.ingredients = ingredients;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public ArrayList<String> getRating() {
        return rating;
    }

    public void setRating(ArrayList<String> rating) {
        this.rating = rating;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }


}
