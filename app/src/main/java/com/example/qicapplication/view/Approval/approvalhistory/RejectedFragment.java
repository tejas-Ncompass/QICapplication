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
import com.example.qicapplication.databinding.FragmentRejectedBinding;
import com.example.qicapplication.viewmodel.Approvals.HistoryViewModel;

import java.util.ArrayList;

public class RejectedFragment extends Fragment {

    private FragmentRejectedBinding binding;
    private ApprovalRecyclerAdapter rejectedFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRejectedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HistoryViewModel viewModel = new ViewModelProvider(requireParentFragment()).get(HistoryViewModel.class);

        binding.leaveRejectedRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        rejectedFragmentAdapter = new ApprovalRecyclerAdapter(requireContext(), new ArrayList<>());
        binding.leaveRejectedRecyclerview.setAdapter(rejectedFragmentAdapter);

        binding.leaveRejectedRecyclerview.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });

        viewModel.getRejectedItems().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                rejectedFragmentAdapter.updateItems(items);
                binding.rejectedCount.setText(String.valueOf(items.size()));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}