package com.example.qicapplication.view.Notifications.notificationfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qicapplication.view.Notifications.NotificationRecycleAdapter;
import com.example.qicapplication.databinding.FragmentPersonalBinding;
import com.example.qicapplication.viewmodel.NotificationsViewModel;

import java.util.ArrayList;

public class PersonalFragment extends Fragment {
    private FragmentPersonalBinding binding;
    private NotificationRecycleAdapter personalAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPersonalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.personalRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        personalAdapter = new NotificationRecycleAdapter(requireContext(), new ArrayList<>());
        binding.personalRecyclerView.setAdapter(personalAdapter);

        NotificationsViewModel viewModel = new ViewModelProvider(requireActivity()).get(NotificationsViewModel.class);
        viewModel.getPersonalNotifications().observe(getViewLifecycleOwner(), items -> {
            personalAdapter.updateItems(items);
        });
    }
}