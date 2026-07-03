package com.example.qicapplication.view.Approval;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.LeaveRequestItemBinding;

public class LeaveViewHolder extends RecyclerView.ViewHolder {

    TextView leaveType, name, designantion, days, date, date_time, statusTxt;
    ImageView profile, statusIcon;
    LeaveRequestItemBinding binding;

    public LeaveViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = LeaveRequestItemBinding.bind(itemView);

        leaveType = binding.leaveTypeText;
        name = binding.titleText;
        designantion = binding.subtext;
        days = binding.dayText;
        date = binding.dateText;
        date_time = binding.dateTimeTextview;
        statusTxt = binding.statusText;
        profile = binding.profileImageView;
        statusIcon = binding.statusIconImage;
    }
}