package com.example.qicapplication.viewmodel.Approvals;


import android.app.Application;
import android.content.res.TypedArray;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qicapplication.R;
import com.example.qicapplication.model.Approval.ViewItems;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ViewItems>> allItems = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<ViewItems>> acceptedItems = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<ViewItems>> rejectedItems = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<ViewItems>> getAllItems() { return allItems; }
    public LiveData<List<ViewItems>> getAcceptedItems() { return acceptedItems; }
    public LiveData<List<ViewItems>> getRejectedItems() { return rejectedItems; }

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        loadHistoryData();
    }

    private void loadHistoryData() {
        String[] leaveType = getApplication().getResources().getStringArray(R.array.leave_type);
        String[] name = getApplication().getResources().getStringArray(R.array.people);
        String[] designation = getApplication().getResources().getStringArray(R.array.designantion);
        String[] days = getApplication().getResources().getStringArray(R.array.days);
        String[] date = getApplication().getResources().getStringArray(R.array.date);
        String[] dateTime = getApplication().getResources().getStringArray(R.array.date_time);
        String[] txtColor = getApplication().getResources().getStringArray(R.array.text_color);
        String[] bgColor = getApplication().getResources().getStringArray(R.array.background_color);
        String[] status = getApplication().getResources().getStringArray(R.array.leave_status);
        String[] statusColor = getApplication().getResources().getStringArray(R.array.status_color);

        TypedArray profileImages = getApplication().getResources().obtainTypedArray(R.array.profile_pictures);
        TypedArray iconResources = getApplication().getResources().obtainTypedArray(R.array.status_icon);

        List<ViewItems> allList = new ArrayList<>();
        List<ViewItems> acceptedList = new ArrayList<>();
        List<ViewItems> rejectedList = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            if (status[i].contains("Approved")){
                int profileResId = profileImages.getResourceId(i, 0);
                int iconResId = iconResources.getResourceId(1, 0);
                ViewItems item = new ViewItems(leaveType[i], name[i], designation[i], days[i], date[i], dateTime[i], txtColor[i], bgColor[i], profileResId, status[i], iconResId, statusColor[1]);

                acceptedList.add(item);
                allList.add(item);
            }
            else if (status[i].contains("Rejected")){
                int profileResId = profileImages.getResourceId(i, 0);
                int iconResId = iconResources.getResourceId(2, 0);
                ViewItems item = new ViewItems(leaveType[i], name[i], designation[i], days[i], date[i], dateTime[i], txtColor[i], bgColor[i], profileResId, status[i], iconResId, statusColor[2]);

                rejectedList.add(item);
                allList.add(item);
            }
        }

        profileImages.recycle();
        iconResources.recycle();

        allItems.setValue(allList);
        acceptedItems.setValue(acceptedList);
        rejectedItems.setValue(rejectedList);
    }
}