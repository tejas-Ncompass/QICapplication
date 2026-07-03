package com.example.qicapplication.view.Dashboard.navigationfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qicapplication.view.Services.InsuranceBottomSheet;
import com.example.qicapplication.databinding.FragmentServicesBinding;

public class ServicesFragment extends Fragment {
    private FragmentServicesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentServicesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setOnApplyWindowInsetsListener(binding.statusBarBackground, (v, insets) -> {
            Insets statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars());
            v.getLayoutParams().height = statusBarInsets.top;
            v.requestLayout();
            return insets;
        });

        binding.openButton.setOnClickListener(v -> {
            InsuranceBottomSheet bottomSheet = InsuranceBottomSheet.newInstance();
            bottomSheet.show(getChildFragmentManager(), InsuranceBottomSheet.TAG);
        });
    }
}
