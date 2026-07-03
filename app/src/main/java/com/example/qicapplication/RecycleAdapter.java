package com.example.qicapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RequestViewHolder>{

    Context context;
    List<ViewRequests> items;

    public RecycleAdapter(Context context, List<ViewRequests> items){
        this.context= context;
        this.items=items;
    }
    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestViewHolder(LayoutInflater.from(context).inflate(R.layout.items_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        holder.requestImage.setImageResource(items.get(position).getImage());
        holder.titleText.setText(items.get(position).getTitle());
        holder.updateText.setText(items.get(position).getUpdatedAt());
        holder.numberText.setText(items.get(position).getNumber());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
