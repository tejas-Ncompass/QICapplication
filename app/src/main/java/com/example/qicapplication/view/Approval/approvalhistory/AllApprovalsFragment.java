package com.example.qicapplication.view.Approval.approvalhistory;

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

import com.example.qicapplication.view.Approval.ApprovalRecyclerAdapter;
import com.example.qicapplication.databinding.FragmentAll2Binding;
import com.example.qicapplication.viewmodel.Approvals.HistoryViewModel;

import java.util.ArrayList;


public class AllApprovalsFragment extends Fragment {

    private FragmentAll2Binding binding;
    private ApprovalRecyclerAdapter allAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAll2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HistoryViewModel viewModel = new ViewModelProvider(requireParentFragment()).get(HistoryViewModel.class);

        binding.leaveAllRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        allAdapter = new ApprovalRecyclerAdapter(requireContext(), new ArrayList<>());
        binding.leaveAllRecyclerview.setAdapter(allAdapter);

        binding.leaveAllRecyclerview.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });

        viewModel.getAllItems().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                allAdapter.updateItems(items);
                binding.allCount.setText(String.valueOf(items.size()));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}