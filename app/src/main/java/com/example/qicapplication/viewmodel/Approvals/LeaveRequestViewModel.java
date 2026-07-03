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

public class LeaveRequestViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ViewItems>> leaveRequests = new MutableLiveData<>();
    private final MutableLiveData<Integer> totalCount = new MutableLiveData<>(0);
    public LiveData<List<ViewItems>> getLeaveRequests() {
        return leaveRequests;
    }
    public LiveData<Integer> getTotalCount() {
        return totalCount;
    }

    public LeaveRequestViewModel(@NonNull Application application) {
        super(application);
        loadLeaveRequests();
    }

    private void loadLeaveRequests() {
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

        List<ViewItems> items = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < name.length; i++) {
            int profileResId = profileImages.getResourceId(i, 0);
            int iconResId = iconResources.getResourceId(0, 0);

            items.add(new ViewItems(
                    leaveType[i], name[i], designation[i], days[i], date[i], dateTime[i],
                    txtColor[i], bgColor[i], profileResId, status[2], iconResId, statusColor[0]
            ));
            count++;
        }

        profileImages.recycle();
        iconResources.recycle();

        leaveRequests.setValue(items);
        totalCount.setValue(count);
    }
}