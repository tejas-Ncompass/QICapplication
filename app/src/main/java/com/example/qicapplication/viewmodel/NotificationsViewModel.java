package com.example.qicapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

// NotificationViewModel.java
import android.app.Application;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qicapplication.R;
import com.example.qicapplication.model.Notifications.ViewNotifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ViewNotifications>> allNotifications = new MutableLiveData<>();
    private final MutableLiveData<List<ViewNotifications>> personalNotifications = new MutableLiveData<>();
    private final MutableLiveData<List<ViewNotifications>> othersNotifications = new MutableLiveData<>();

    public NotificationsViewModel(@NonNull Application application) {
        super(application);
        loadNotifications();
    }

    public LiveData<List<ViewNotifications>> getAllNotifications() { return allNotifications; }
    public LiveData<List<ViewNotifications>> getPersonalNotifications() { return personalNotifications; }
    public LiveData<List<ViewNotifications>> getOthersNotifications() { return othersNotifications; }

    private void loadNotifications() {
        List<ViewNotifications> allList = new ArrayList<>();
        List<ViewNotifications> personalList = new ArrayList<>();
        List<ViewNotifications> othersList = new ArrayList<>();

        String[] titles = getApplication().getResources().getStringArray(R.array.notification_title);
        String[] subtexts = getApplication().getResources().getStringArray(R.array.notification_subtext);
        String[] times = getApplication().getResources().getStringArray(R.array.notification_time);
        String[] filters = getApplication().getResources().getStringArray(R.array.notification_filter);

        TypedArray logoResources = getApplication().getResources().obtainTypedArray(R.array.notification_images);
        TypedArray imageResources = getApplication().getResources().obtainTypedArray(R.array.extra_image);

        for (int i = 0; i < titles.length; i++) {
            int logoResId = logoResources.getResourceId(i, 0);
            int imageResId = imageResources.getResourceId(i, 0);

            ViewNotifications item = new ViewNotifications(
                    titles[i], subtexts[i], times[i], logoResId, imageResId, filters[i]
            );

            allList.add(item);

            if (filters[i].contains("personal")) {
                personalList.add(item);
            } else if (filters[i].contains("others")) {
                othersList.add(item);
            }
        }

        logoResources.recycle();
        imageResources.recycle();

        allNotifications.setValue(allList);
        personalNotifications.setValue(personalList);
        othersNotifications.setValue(othersList);
    }
}
