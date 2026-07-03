package com.example.qicapplication.viewmodel.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class SetPinViewModel extends ViewModel {

    public enum PinSuccess {INVALID, SUCCESS, SKIP}

    private final MutableLiveData<PinSuccess> pinSuccessStatus = new MutableLiveData<>();
    public LiveData<PinSuccess> getPinSuccessStatus() {
        return pinSuccessStatus;
    }

    public void validatePin(String pin) {
        if (pin == null || pin.length() != 4){
            this.pinSuccessStatus.setValue(PinSuccess.INVALID);
            return;
        }
        this.pinSuccessStatus.setValue(PinSuccess.SUCCESS);
    }
    public void skipPin() {
        pinSuccessStatus.setValue(PinSuccess.SKIP);
    }
    public void clearPinEvent() {
        pinSuccessStatus.setValue(null);
    }
}