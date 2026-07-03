package com.example.qicapplication.model.Notifications;

public class ViewNotifications {

    String title, subtext, time, filter;
    int logo, image=0;

    public ViewNotifications(String title,String subtext, String time,int logo, int image, String filter)
    {
        this.title= title;
        this.subtext= subtext;
        this.time= time;
        this.logo = logo;
        this.image=image;
        this.filter=filter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFilter() {
        return filter;
    }
    public void setFilter(String filter) {
        this.filter = filter;
    }
}
