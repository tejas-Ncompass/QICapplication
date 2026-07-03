package com.example.qicapplication.view.Notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qicapplication.R;
import com.example.qicapplication.model.Notifications.ViewNotifications;

import java.util.List;
public class NotificationRecycleAdapter extends RecyclerView.Adapter<NotificationsViewHolder> {
    private final Context context;
    private final List<ViewNotifications> items;
    public NotificationRecycleAdapter(Context context, List<ViewNotifications> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_items, parent, false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        ViewNotifications currentItem = items.get(position);

        holder.title.setText(currentItem.getTitle());
        holder.subtext.setText(currentItem.getSubtext());
        holder.time.setText(currentItem.getTime());
        holder.logo.setImageResource(currentItem.getLogo());

        if (currentItem.getImage() != 0) {
            holder.imageCard.setVisibility(View.VISIBLE);
            holder.image.setImageResource(currentItem.getImage());
        } else {
            holder.imageCard.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void updateItems(List<ViewNotifications> newItems) {
        this.items.clear();
        this.items.addAll(newItems);
        notifyDataSetChanged();
    }
}
