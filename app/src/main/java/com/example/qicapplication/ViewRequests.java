package com.example.qicapplication;

public class ViewRequests {

    int image;
    String title, updatedAt, number;

    public ViewRequests(int image, String title,String updatedAt,String number)
    {
        this.image= image;
        this.title= title;
        this.updatedAt = updatedAt;
        this.number = number;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
