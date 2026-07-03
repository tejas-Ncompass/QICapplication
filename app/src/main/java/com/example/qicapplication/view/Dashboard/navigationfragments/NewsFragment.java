package com.example.qicapplication.view.Dashboard.navigationfragments;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qicapplication.view.News.NewsRecycleAdapter;
import com.example.qicapplication.R;
import com.example.qicapplication.model.News.ViewNews;
import com.example.qicapplication.databinding.FragmentNewsBinding;
import com.example.qicapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private NewsViewModel viewModel;
    private NewsRecycleAdapter newsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        View statusBarBg = binding.statusBarBackground;
        ViewCompat.setOnApplyWindowInsetsListener(statusBarBg, (v, insets) -> {
            Insets statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars());
            v.getLayoutParams().height = statusBarInsets.top;
            v.requestLayout();
            return insets;
        });

        newsAdapter = new NewsRecycleAdapter(requireContext(), () -> {
            viewModel.dismissDialog();
        });

        RecyclerView newsRecycleView = binding.newsRecyclerview;
        newsRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        newsRecycleView.setAdapter(newsAdapter);

        viewModel.getNewsItems().observe(getViewLifecycleOwner(), items -> {
            newsAdapter.updateNewsItems(items);
        });

        if (viewModel.getNewsItems().getValue() == null) {
            loadInitialNewsData();
        }

        newsRecycleView.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });
    }

    private void loadInitialNewsData() {

        String[] newsTitle = getResources().getStringArray(R.array.news_title);
        String[] subtitle = getResources().getStringArray(R.array.news_subtitle);
        String[] contentHeading = getResources().getStringArray(R.array.content_heading);
        String[] contentTxt = getResources().getStringArray(R.array.news_content);
        TypedArray coverImageResources = getResources().obtainTypedArray(R.array.cover_image);
        TypedArray logoImageResources = getResources().obtainTypedArray(R.array.news_logo);

        List<ViewNews> newsList = new ArrayList<>();

        for (int i = 0; i < newsTitle.length; i++) {
            int coverImageResId = coverImageResources.getResourceId(i, 0);
            int logoResId = logoImageResources.getResourceId(i, 0);

            newsList.add(new ViewNews(coverImageResId, logoResId, newsTitle[i], subtitle[i], contentHeading[i], contentTxt[i]));
        }

        coverImageResources.recycle();
        logoImageResources.recycle();

        viewModel.initNewsData(newsList);
    }
}