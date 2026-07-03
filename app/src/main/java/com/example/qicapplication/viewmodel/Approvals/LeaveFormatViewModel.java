package com.example.qicapplication.viewmodel.Approvals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LeaveFormatViewModel extends ViewModel {

    public enum NavigationEvent {
        APPROVERS, DOCUMENTS, ATTACHMENTS, CLOSE
    }
    private final MutableLiveData<NavigationEvent> navigationAction = new MutableLiveData<>();
    public LiveData<NavigationEvent> getNavigationAction() {
        return navigationAction;
    }
    public void onApproversClicked() {
        navigationAction.setValue(NavigationEvent.APPROVERS);
    }
    public void onDocumentsClicked() {
        navigationAction.setValue(NavigationEvent.DOCUMENTS);
    }
    public void onAttachmentsClicked() {navigationAction.setValue(NavigationEvent.ATTACHMENTS);}
    public void onSubmitAction() {
        navigationAction.setValue(NavigationEvent.CLOSE);
    }
    public void clearNavigationAction() {
        navigationAction.setValue(null);
    }
}