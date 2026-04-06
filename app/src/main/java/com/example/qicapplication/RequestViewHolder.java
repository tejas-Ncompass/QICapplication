package com.example.qicapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequestViewHolder extends RecyclerView.ViewHolder{

    ImageView requestImage;
    TextView titleText, updateText, numberText;

    public RequestViewHolder(@NonNull View itemView) {
        super(itemView);

        requestImage = itemView.findViewById(R.id.imageView);
        titleText = itemView.findViewById(R.id.textView);
        updateText = itemView.findViewById(R.id.subtext);
        numberText = itemView.findViewById(R.id.number_textview);
    }
}
