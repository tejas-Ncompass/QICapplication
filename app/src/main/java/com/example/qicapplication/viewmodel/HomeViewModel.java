package com.example.qicapplication.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qicapplication.R;
import com.example.qicapplication.model.Home.ViewRequests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ViewRequests>> requestsList = new MutableLiveData<>();
    private final MutableLiveData<String> profileImagePath = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        loadRequestsData();
        loadProfileImagePath();
    }

    public LiveData<List<ViewRequests>> getRequestsList() {
        return requestsList;
    }
    public LiveData<String> getProfileImagePath() {
        return profileImagePath;
    }

    private void loadRequestsData() {
        Context context = getApplication().getApplicationContext();
        String[] title = context.getResources().getStringArray(R.array.requests);
        String[] updatedAt = context.getResources().getStringArray(R.array.update_time);
        String[] number = context.getResources().getStringArray(R.array.numbers);
        String[] tag = context.getResources().getStringArray(R.array.tag);

        TypedArray requestImageResources = context.getResources().obtainTypedArray(R.array.request_images);
        List<ViewRequests> items = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {
            int imageResId = requestImageResources.getResourceId(i, 0);
            items.add(new ViewRequests(imageResId, title[i], updatedAt[i], number[i], tag[i]));
        }

        requestImageResources.recycle();
        requestsList.setValue(items);
    }

    public void loadProfileImagePath() {
        Context context = getApplication().getApplicationContext();
        SharedPreferences prefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        String path = prefs.getString("profile_path", null);

        if (path != null && new File(path).exists()) {
            profileImagePath.setValue(path);
        } else {
            profileImagePath.setValue(null);
        }
    }
}
