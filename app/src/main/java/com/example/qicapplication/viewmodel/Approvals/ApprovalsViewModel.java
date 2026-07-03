package com.example.qicapplication.viewmodel.Approvals;


import android.app.Application;
import android.content.res.TypedArray;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qicapplication.R;
import com.example.qicapplication.model.Home.ViewRequests;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ApprovalsViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ViewRequests>> requestsLiveData = new MutableLiveData<>();
    public LiveData<List<ViewRequests>> getRequestsLiveData() {
        return requestsLiveData;
    }

    public ApprovalsViewModel(@NonNull Application application) {
        super(application);
        loadRequests();
    }

    private void loadRequests() {
        String[] titles = getApplication().getResources().getStringArray(R.array.requests);
        String[] updatedAts = getApplication().getResources().getStringArray(R.array.update_time);
        String[] numbers = getApplication().getResources().getStringArray(R.array.numbers);
        String[] tags = getApplication().getResources().getStringArray(R.array.tag);

        TypedArray requestImageResources = getApplication().getResources().obtainTypedArray(R.array.request_images);

        List<ViewRequests> items = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            int imageResId = requestImageResources.getResourceId(i, 0);
            items.add(new ViewRequests(imageResId, titles[i], updatedAts[i], numbers[i], tags[i]));
        }
        requestImageResources.recycle();

        requestsLiveData.setValue(items);
    }
}