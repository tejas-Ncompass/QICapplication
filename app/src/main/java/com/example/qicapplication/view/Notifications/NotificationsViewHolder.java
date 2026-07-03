package com.example.qicapplication.view.Notifications;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qicapplication.R;

public class NotificationsViewHolder extends RecyclerView.ViewHolder {
    ImageView logo, image;
    TextView title, subtext, time;
    CardView imageCard;


    public NotificationsViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title_text_notification);
        subtext = itemView.findViewById(R.id.sub_text);
        time = itemView.findViewById(R.id.time_text);
        logo = itemView.findViewById(R.id.logo_view);
        image = itemView.findViewById(R.id.image_extra);
        imageCard = itemView.findViewById(R.id.extra_image_card);
    }
}
