package com.example.qicapplication.view.Approval;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qicapplication.databinding.FragmentLeaveRequestBinding;
import com.example.qicapplication.viewmodel.Approvals.LeaveRequestViewModel;

import java.util.ArrayList;

public class LeaveRequestFragment extends Fragment {

    private FragmentLeaveRequestBinding binding;
    private ApprovalRecyclerAdapter leaveRequestAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLeaveRequestBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LeaveRequestViewModel viewModel = new ViewModelProvider(this).get(LeaveRequestViewModel.class);

        binding.leaveRequestesRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        leaveRequestAdapter = new ApprovalRecyclerAdapter(requireContext(), new ArrayList<>());
        binding.leaveRequestesRecyclerview.setAdapter(leaveRequestAdapter);

        binding.leaveRequestesRecyclerview.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });

        viewModel.getLeaveRequests().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                leaveRequestAdapter.updateItems(items);
            }
        });

        viewModel.getTotalCount().observe(getViewLifecycleOwner(), count ->
                binding.leaveRequestCount.setText(String.valueOf(count))
        );
    }

}