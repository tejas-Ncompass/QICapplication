package com.example.qicapplication.model.Home;

public class ViewRequests {

    int image;
    String title, updatedAt, number, tag;

    public ViewRequests(int image, String title,String updatedAt,String number,String tag)
    {
        this.image= image;
        this.title= title;
        this.updatedAt = updatedAt;
        this.number = number;
        this.tag=tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
