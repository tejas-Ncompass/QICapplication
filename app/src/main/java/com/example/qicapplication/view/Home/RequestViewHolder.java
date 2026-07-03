package com.example.qicapplication.view.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.ItemsHomeBinding;
import com.example.qicapplication.databinding.LeaveRequestItemBinding;

public class RequestViewHolder extends RecyclerView.ViewHolder{

    ImageView requestImage;
    TextView titleText, updateText, numberText, tag;
    ItemsHomeBinding itemsBinding;



    public RequestViewHolder(@NonNull View itemView) {
        super(itemView);
        itemsBinding = ItemsHomeBinding.bind(itemView);

        requestImage = itemsBinding.imageView;
        titleText = itemsBinding.textView;
        updateText = itemsBinding.subtext;
        numberText = itemsBinding.numberTextview;
        tag = itemView.findViewById(R.id.status_text);
    }
}
