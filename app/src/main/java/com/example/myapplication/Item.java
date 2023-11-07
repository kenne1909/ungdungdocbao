package com.example.myapplication;

public class Item {
    private String ten;
    private String date;
    private String image;

    public Item(String ten, String date, String image) {
        this.ten = ten;
        this.date = date;
        this.image = image;
    }

    public Item() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
