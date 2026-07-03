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
import com.example.qicapplication.databinding.FragmentAllBinding;
import com.example.qicapplication.viewmodel.NotificationsViewModel;

import java.util.ArrayList;

public class AllFragment extends Fragment {
    private FragmentAllBinding binding;
    private NotificationRecycleAdapter notificationsRecyclerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAllBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        notificationsRecyclerAdapter = new NotificationRecycleAdapter(requireContext(), new ArrayList<>());
        binding.recyclerView.setAdapter(notificationsRecyclerAdapter);

        NotificationsViewModel viewModel = new ViewModelProvider(requireActivity()).get(NotificationsViewModel.class);
        viewModel.getAllNotifications().observe(getViewLifecycleOwner(), items -> {
            notificationsRecyclerAdapter.updateItems(items);
        });
    }
}