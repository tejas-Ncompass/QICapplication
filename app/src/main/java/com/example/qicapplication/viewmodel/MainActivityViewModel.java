package com.example.qicapplication.viewmodel;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<Integer> progressData = new MutableLiveData<>(0);
    private final MutableLiveData<Boolean> navigateToLogin = new MutableLiveData<>(false);
    private CountDownTimer timer;
    public MainActivityViewModel() {
        startSplashTimer();
    }
    public LiveData<Integer> getProgressData() {
        return progressData;
    }
    public LiveData<Boolean> getNavigateToLogin() {
        return navigateToLogin;
    }

    private void startSplashTimer() {
        timer = new CountDownTimer(2000, 20) {
            @Override
            public void onTick(long millisUntilFinished) {
                int currentProgress = (int) (((2000 - millisUntilFinished) * 100) / 2000);
                progressData.setValue(currentProgress);
            }
            @Override
            public void onFinish() {
                progressData.setValue(100);
                navigateToLogin.setValue(true);
            }
        };
        timer.start();
    }
}