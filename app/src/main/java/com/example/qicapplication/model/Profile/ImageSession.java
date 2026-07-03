package com.example.qicapplication.model.Profile;

import java.io.File;

public class ImageSession {
    private static ImageSession instance;
    private File savedFile;

    private ImageSession() {}

    public static synchronized ImageSession getInstance() {
        if (instance == null) {
            instance = new ImageSession();
        }
        return instance;
    }

    public File getSavedFile() { return savedFile; }
    public void setSavedFile(File file) { this.savedFile = file; }
}
