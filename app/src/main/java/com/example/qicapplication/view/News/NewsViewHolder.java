package com.example.qicapplication.view.News;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.NewsComponentsBinding;

public class NewsViewHolder extends RecyclerView.ViewHolder{

    ImageView coverImage, logo;
    TextView newsTitle, subtitle, contentHeading, contentTxt;
    NewsComponentsBinding binding;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = NewsComponentsBinding.bind(itemView);

        coverImage= binding.newsImage;
        logo= binding.newsLogo;
        newsTitle = binding.newsTitle;
        subtitle = binding.newsSubtitle;
        contentHeading= binding.newsContentHeading;
        contentTxt= binding.newsContent;
    }
}
