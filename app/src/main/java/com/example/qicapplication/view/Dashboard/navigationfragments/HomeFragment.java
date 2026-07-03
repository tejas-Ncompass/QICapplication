package com.example.qicapplication.view.Dashboard.navigationfragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qicapplication.view.Notifications.NotificationActivity;
import com.example.qicapplication.view.Profile.ProfileActivity;
import com.example.qicapplication.view.Home.RecycleAdapter;
import com.example.qicapplication.databinding.FragmentHomeBinding;
import com.example.qicapplication.viewmodel.HomeViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private RecycleAdapter homeAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        setupRecyclerView();
        setupObservers();

        binding.profileButton.setOnClickListener(v -> openProfilePage());
        binding.homeProfileImage.setOnClickListener(v -> openProfilePage());
        binding.bellButton.setOnClickListener(v -> openNotificationsPage());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupRecyclerView() {
        homeAdapter = new RecycleAdapter(requireContext(), new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(homeAdapter);

        binding.recyclerView.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });
    }

    private void setupObservers() {
        viewModel.getRequestsList().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                homeAdapter.updateListItems(items);
            }
        });

        viewModel.getProfileImagePath().observe(getViewLifecycleOwner(), path -> {
            if (path != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                binding.homeProfileImage.setImageBitmap(bitmap);
            }
        });
    }
    public void openNotificationsPage() {
        startActivity(new Intent(requireContext(), NotificationActivity.class));
    }

    public void openProfilePage() {
        startActivity(new Intent(requireContext(), ProfileActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadProfileImagePath();
    }

}