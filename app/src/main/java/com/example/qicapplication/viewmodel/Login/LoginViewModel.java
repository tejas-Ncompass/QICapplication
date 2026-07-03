package com.example.qicapplication.viewmodel.Login;

import android.util.Patterns;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    public enum LoginSuccesState { INVALID_EMAIL, INVALID_PASSWORD, SUCCESS }

    private final MutableLiveData<Boolean> isLoginButtonEnabled = new MutableLiveData<>(false);
    private final MutableLiveData<LoginSuccesState> loginResult = new MutableLiveData<>();
    private String currentEmail = "";
    private String currentPassword = "";
    public LiveData<Boolean> getIsLoginButtonEnabled() {
        return isLoginButtonEnabled;
    }
    public LiveData<LoginSuccesState> getLoginResult() {
        return loginResult;
    }

    public void onInputChanged(String email, String password) {
        currentEmail = email.trim();
        currentPassword = password.trim();
        isLoginButtonEnabled.setValue(!currentEmail.isEmpty() && !currentPassword.isEmpty());
    }

    public void attemptLogin() {
        if (!Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()) {
            loginResult.setValue(LoginSuccesState.INVALID_EMAIL);
            return;
        }
        if (currentPassword.length() < 8) {
            loginResult.setValue(LoginSuccesState.INVALID_PASSWORD);
            return;
        }
        loginResult.setValue(LoginSuccesState.SUCCESS);
    }

    public void clearLoginResult() {
        loginResult.setValue(null);
    }
}