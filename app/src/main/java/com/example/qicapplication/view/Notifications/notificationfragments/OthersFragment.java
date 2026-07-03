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
import com.example.qicapplication.databinding.FragmentOthersBinding;
import com.example.qicapplication.viewmodel.NotificationsViewModel;

import java.util.ArrayList;

public class OthersFragment extends Fragment {
    private FragmentOthersBinding binding;
    private NotificationRecycleAdapter othersAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOthersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.othersRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        othersAdapter = new NotificationRecycleAdapter(requireContext(), new ArrayList<>());
        binding.othersRecyclerView.setAdapter(othersAdapter);

        NotificationsViewModel viewModel = new ViewModelProvider(requireActivity()).get(NotificationsViewModel.class);
        viewModel.getOthersNotifications().observe(getViewLifecycleOwner(), items -> {
            othersAdapter.updateItems(items);
        });
    }
}