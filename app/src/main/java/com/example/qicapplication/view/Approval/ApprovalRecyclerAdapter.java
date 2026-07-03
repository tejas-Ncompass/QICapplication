package com.example.qicapplication.view.Approval;

import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qicapplication.model.Approval.ViewItems;
import com.example.qicapplication.R;

import java.util.List;

public class ApprovalRecyclerAdapter extends RecyclerView.Adapter<LeaveViewHolder> {

    private final Context context;
    private List<ViewItems> items;

    public ApprovalRecyclerAdapter(Context context, List<ViewItems> items) {
        this.context = context;
        this.items = items;
    }

    public void updateItems(List<ViewItems> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaveViewHolder(LayoutInflater.from(context).inflate(R.layout.leave_request_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveViewHolder holder, int position) {
        ViewItems item = items.get(position);

        holder.leaveType.setText(item.getLeaveType());
        holder.leaveType.setTextColor(Color.parseColor(item.getTxtColor()));
        holder.leaveType.setBackgroundColor(Color.parseColor(item.getBgColor()));

        holder.name.setText(item.getName());
        holder.designantion.setText(item.getDesignantion());
        holder.days.setText(item.getDays());
        holder.date.setText(item.getDate());
        holder.date_time.setText(item.getDate_time());
        holder.profile.setImageResource(item.getProfile());

        if (item.getStatus() == null || item.getStatus().isEmpty() || "#FFFFFF".equalsIgnoreCase(item.getStatusColor())) {
            holder.statusTxt.setVisibility(View.GONE);
            holder.statusIcon.setVisibility(View.GONE);
        } else {
            holder.statusTxt.setVisibility(View.VISIBLE);
            holder.statusIcon.setVisibility(View.VISIBLE);
            holder.statusTxt.setText(item.getStatus());
            holder.statusTxt.setTextColor(Color.parseColor(item.getStatusColor()));
            holder.statusIcon.setImageResource(item.getStatusIcon());
        }

        holder.itemView.setOnClickListener(v -> {
            if ("Nico Roseberg".equals(item.getName())) {
                context.startActivity(new Intent(context, LeaveFormatActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}