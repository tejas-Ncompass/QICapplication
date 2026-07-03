package com.example.qicapplication.view.Services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.qicapplication.databinding.InsuranceBottomDialogBinding;
import com.example.qicapplication.viewmodel.ServiceViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class InsuranceBottomSheet extends BottomSheetDialogFragment {

    public static final String TAG = "InsuranceBottomSheet";
    private InsuranceBottomDialogBinding binding;
    private ServiceViewModel viewModel;
    private InsuranceRecyclerAdapter serviceAdapter;

    public static InsuranceBottomSheet newInstance() {
        return new InsuranceBottomSheet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = InsuranceBottomDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ServiceViewModel.class);

        setupRecyclerView();
        setupSearchView();
        setupObservers();

        binding.closeInsuranceDialog.setOnClickListener(v -> dismiss());
    }

    private void setupRecyclerView() {
        serviceAdapter = new InsuranceRecyclerAdapter(new ArrayList<>());
        binding.insuranceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.insuranceRecyclerView.setAdapter(serviceAdapter);
    }

    private void setupSearchView() {
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.filterList(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
    }

    private void setupObservers() {
        viewModel.getFilteredList().observe(getViewLifecycleOwner(), filteredItems -> {
            if (filteredItems != null) {
                serviceAdapter.setFilteredList(filteredItems);
            }
        });
    }
}