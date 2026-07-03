package com.example.qicapplication.viewmodel.Approvals;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qicapplication.R;

public class ApprovalPageViewModel extends ViewModel {

    public enum TabState {
        LEAVE_REQUEST, HISTORY
    }

    private final MutableLiveData<TabState> currentTabState = new MutableLiveData<>(TabState.LEAVE_REQUEST);
    public LiveData<TabState> getCurrentTabState() {
        return currentTabState;
    }

    public void selectTab(int checkedId) {
        if (checkedId == R.id.leave_requestes_button) {
            if (currentTabState.getValue() != TabState.LEAVE_REQUEST) {
                currentTabState.setValue(TabState.LEAVE_REQUEST);
            }
        } else if (checkedId == R.id.history_button) {
            if (currentTabState.getValue() != TabState.HISTORY) {
                currentTabState.setValue(TabState.HISTORY);
            }
        }
    }
}