package com.example.qicapplication.view.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qicapplication.R;
import com.example.qicapplication.model.News.NewsItemWrapper;
import com.example.qicapplication.model.News.NewsListItems;
import com.example.qicapplication.model.News.ViewNews;

import java.util.ArrayList;
import java.util.List;

public class NewsRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<NewsListItems> items = new ArrayList<>();
    private final OnDialogCloseListener closeListener;
    public interface OnDialogCloseListener {
        void onDialogClosed();
    }

    public NewsRecycleAdapter(Context context, OnDialogCloseListener closeListener) {
        this.context = context;
        this.closeListener = closeListener;
    }

    public void updateNewsItems(List<NewsListItems> newItems) {
        this.items.clear();
        this.items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NewsListItems.TYPE_DIALOG) {
            return new DialogViewHolder(LayoutInflater.from(context).inflate(R.layout.news_dialog, parent, false));
        } else {
            return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_components, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsListItems item = items.get(position);

        if (holder instanceof DialogViewHolder) {
            DialogViewHolder dialogHolder = (DialogViewHolder) holder;
            dialogHolder.closeIcon.setOnClickListener(v -> {
                if (closeListener != null) {
                    closeListener.onDialogClosed();
                }
            });
        }

        if (holder instanceof NewsViewHolder) {
            NewsViewHolder newsHolder = (NewsViewHolder) holder;
            ViewNews newsData = ((NewsItemWrapper) item).getNewsData();

            newsHolder.coverImage.setImageResource(newsData.getCoverImage());
            newsHolder.logo.setImageResource(newsData.getLogo());
            newsHolder.newsTitle.setText(newsData.getNewsTitle());
            newsHolder.subtitle.setText(newsData.getSubtitle());
            newsHolder.contentHeading.setText(newsData.getContentHeading());
            newsHolder.contentTxt.setText(newsData.getContentTxt());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


class DialogViewHolder extends RecyclerView.ViewHolder {
    ImageView closeIcon;
    public DialogViewHolder(View itemView) {
        super(itemView);
        closeIcon= itemView.findViewById(R.id.close_dialog);
    }
}
