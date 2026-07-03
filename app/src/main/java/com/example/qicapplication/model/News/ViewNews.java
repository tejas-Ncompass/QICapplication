package com.example.qicapplication.model.News;

public class ViewNews {
    int coverImage, logo;
    String newsTitle, subtitle, contentHeading, contentTxt;

    public ViewNews(int coverImage, int logo, String newsTitle, String subtitle, String contentHeading, String contentTxt) {
        this.coverImage = coverImage;
        this.logo = logo;
        this.newsTitle = newsTitle;
        this.subtitle = subtitle;
        this.contentHeading = contentHeading;
        this.contentTxt = contentTxt;
    }

    public int getCoverImage() {
        return coverImage;
    }
    public void setCoverImage(int coverImage) {
        this.coverImage = coverImage;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContentHeading() {
        return contentHeading;
    }

    public void setContentHeading(String contentHeading) {
        this.contentHeading = contentHeading;
    }

    public String getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }
}