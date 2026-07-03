package com.example.qicapplication.view.Dashboard.navigationfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qicapplication.view.Home.RecycleAdapter;
import com.example.qicapplication.databinding.FragmentApprovalsBinding;
import com.example.qicapplication.viewmodel.Approvals.ApprovalsViewModel;

import java.util.ArrayList;

public class ApprovalsFragment extends Fragment {

    private FragmentApprovalsBinding binding;
    private ApprovalsViewModel viewModel;
    private RecycleAdapter approvalsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentApprovalsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ApprovalsViewModel.class);

        View statusBarBg = binding.statusBarBackground;

        ViewCompat.setOnApplyWindowInsetsListener(statusBarBg, (v, insets) -> {
            Insets statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars());
            v.getLayoutParams().height = statusBarInsets.top;
            v.requestLayout();
            return insets;
        });

        RecyclerView approvalRecyclerView = binding.approvalsRecyclerView;
        approvalRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        approvalsAdapter = new RecycleAdapter(requireContext(), new ArrayList<>());
        approvalRecyclerView.setAdapter(approvalsAdapter);

        viewModel.getRequestsLiveData().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                approvalsAdapter.updateListItems(items);
            }
        });

        approvalRecyclerView.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });
    }
}