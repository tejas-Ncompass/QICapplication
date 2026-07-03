package com.example.qicapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qicapplication.model.News.DialogItem;
import com.example.qicapplication.model.News.NewsItemWrapper;
import com.example.qicapplication.model.News.NewsListItems;
import com.example.qicapplication.model.News.ViewNews;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {
    private final MutableLiveData<List<NewsListItems>> newsItems = new MutableLiveData<>();
    private final List<ViewNews> newsList = new ArrayList<>();
    private boolean isDialogOpen = true;
    public LiveData<List<NewsListItems>> getNewsItems() {
        return newsItems;
    }

    public void initNewsData(List<ViewNews> newsList) {
        this.newsList.clear();
        this.newsList.addAll(newsList);
        buildUiList();
    }
    public void dismissDialog() {
        if (isDialogOpen) {
            isDialogOpen = false;
            buildUiList();
        }
    }
    private void buildUiList() {
        List<NewsListItems> items = new ArrayList<>();
        if (isDialogOpen) {
            items.add(new DialogItem());
        }
        for (ViewNews news : newsList) {
            items.add(new NewsItemWrapper(news));
        }
        newsItems.setValue(items);
    }
}
