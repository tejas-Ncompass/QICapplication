package com.example.qicapplication.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qicapplication.model.Profile.ImageSession;

import java.io.File;
import java.io.FileOutputStream;
public class ProfileViewModel extends AndroidViewModel {

    private final MutableLiveData<Bitmap> profileBitmap = new MutableLiveData<>();
    private final MutableLiveData<Boolean> logoutEvent = new MutableLiveData<>(false);
    private final SharedPreferences preferences;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        preferences = application.getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        loadProfileImage();
    }

    public LiveData<Bitmap> getProfileBitmap() {
        return profileBitmap;
    }

    public LiveData<Boolean> getLogoutEvent() {
        return logoutEvent;
    }

    public void logout() {
        logoutEvent.setValue(true);
    }

    private void loadProfileImage() {
        String path = preferences.getString("profile_path", null);
        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                profileBitmap.setValue(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }
        }
    }

    public void saveProfileImage(Bitmap bitmap) {
        if (bitmap == null) return;

        try {
            File savedFile = new File(getApplication().getFilesDir(), "profile_picture.jpg");
            FileOutputStream fileout = new FileOutputStream(savedFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileout);
            fileout.close();

            preferences.edit().putString("profile_path", savedFile.getAbsolutePath()).apply();
            ImageSession.getInstance().setSavedFile(savedFile);

            profileBitmap.setValue(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}