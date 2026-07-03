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
import com.example.qicapplication.databinding.FragmentAcceptedBinding;
import com.example.qicapplication.viewmodel.Approvals.HistoryViewModel;

import java.util.ArrayList;


public class AcceptedFragment extends Fragment {

    private FragmentAcceptedBinding binding;
    private ApprovalRecyclerAdapter acceptedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAcceptedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HistoryViewModel viewModel = new ViewModelProvider(requireParentFragment()).get(HistoryViewModel.class);

        binding.leaveApprovedRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        acceptedAdapter = new ApprovalRecyclerAdapter(requireContext(), new ArrayList<>());
        binding.leaveApprovedRecyclerview.setAdapter(acceptedAdapter);

        binding.leaveApprovedRecyclerview.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });

        viewModel.getAcceptedItems().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                acceptedAdapter.updateItems(items);
                binding.acceptedCount.setText(String.valueOf(items.size()));
            }
        });
    }
}