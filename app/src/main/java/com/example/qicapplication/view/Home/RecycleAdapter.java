package com.example.qicapplication.view.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qicapplication.model.Home.ViewRequests;
import com.example.qicapplication.view.Approval.ApprovalActivity;
import com.example.qicapplication.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RequestViewHolder> {

    private final Context context;
    private List<ViewRequests> items;

    public RecycleAdapter(Context context, List<ViewRequests> items) {
        this.context = context;
        this.items = items;
    }

    public void updateListItems(List<ViewRequests> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestViewHolder(LayoutInflater.from(context).inflate(R.layout.items_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        ViewRequests item = items.get(position);

        holder.requestImage.setImageResource(item.getImage());
        holder.titleText.setText(item.getTitle());
        holder.updateText.setText(item.getUpdatedAt());
        holder.numberText.setText(item.getNumber());

        holder.itemView.setOnClickListener(v -> {
            if ("leave".equals(item.getTag())) {
                Intent intent = new Intent(context, ApprovalActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}