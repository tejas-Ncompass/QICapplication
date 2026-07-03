package com.example.qicapplication.view.Approval;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.FragmentHistoryBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HistoryFragment extends Fragment {
    private FragmentHistoryBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.viewPagerHome.setAdapter(new ViewPagerAdapter(this));

        new TabLayoutMediator(binding.tablayoutHome, binding.viewPagerHome, (tab, position) -> {
            if (position == 0) tab.setText(R.string.all);
            else if (position == 1) tab.setText(R.string.approved);
            else if (position == 2) tab.setText(R.string.rejected);
        }).attach();
    }
}