package com.example.qicapplication.view.Services;

import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.view.LayoutInflater;
import com.example.qicapplication.databinding.InsuranceViewsBinding;
import com.example.qicapplication.model.Services.ViewInsurances;

public class InsuranceRecyclerAdapter extends RecyclerView.Adapter<InsuranceRecyclerAdapter.InsuranceViewHolder> {

    private List<ViewInsurances> items;

    public InsuranceRecyclerAdapter(List<ViewInsurances> items) {
        this.items = items;
    }
    public void setFilteredList(List<ViewInsurances> filteredItems) {
        this.items = filteredItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InsuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InsuranceViewsBinding binding = InsuranceViewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new InsuranceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InsuranceViewHolder holder, int position) {
        ViewInsurances item = items.get(position);
        holder.binding.insuranceTitle.setText(item.getInsuranceTitle());
        holder.binding.insuranceSubtext.setText(item.getSubtxt());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class InsuranceViewHolder extends RecyclerView.ViewHolder {
        final InsuranceViewsBinding binding;

        public InsuranceViewHolder(@NonNull InsuranceViewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

