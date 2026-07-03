package com.example.qicapplication.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qicapplication.R;
import com.example.qicapplication.model.Services.ViewInsurances;

import java.util.ArrayList;
import java.util.List;

public class ServiceViewModel extends AndroidViewModel {

    private final List<ViewInsurances> originalList = new ArrayList<>();
    private final MutableLiveData<List<ViewInsurances>> filteredList = new MutableLiveData<>();

    public ServiceViewModel(@NonNull Application application) {
        super(application);
        loadInsuranceData();
    }

    public LiveData<List<ViewInsurances>> getFilteredList() {
        return filteredList;
    }

    private void loadInsuranceData() {
        Context context = getApplication().getApplicationContext();
        String[] titles = context.getResources().getStringArray(R.array.insurance_types);
        String[] subs = context.getResources().getStringArray(R.array.subtext);

        originalList.clear();
        for (int i = 0; i < titles.length; i++) {
            originalList.add(new ViewInsurances(titles[i], subs[i]));
        }

        filteredList.setValue(new ArrayList<>(originalList));
    }

    public void filterList(String query) {
        if (query == null || query.trim().isEmpty()) {
            filteredList.setValue(new ArrayList<>(originalList));
            return;
        }

        String lowerCaseQuery = query.toLowerCase().trim();
        List<ViewInsurances> filtered = new ArrayList<>();

        for (ViewInsurances item : originalList) {
            boolean matchesTitle = item.getInsuranceTitle().toLowerCase().contains(lowerCaseQuery);
            boolean matchesSubtext = item.getSubtxt().toLowerCase().contains(lowerCaseQuery);

            if (matchesTitle || matchesSubtext) {
                filtered.add(item);
            }
        }
        filteredList.setValue(filtered);
    }
}
