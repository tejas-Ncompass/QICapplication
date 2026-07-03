package com.example.qicapplication.model.News;

public class NewsItemWrapper implements NewsListItems {
    private final ViewNews newsData;
    public NewsItemWrapper(ViewNews newsData)
    {
        this.newsData = newsData;
    }
    public ViewNews getNewsData(){
        return newsData;
    }

    @Override
    public int getViewType(){
        return TYPE_NEWS;
    }
}
