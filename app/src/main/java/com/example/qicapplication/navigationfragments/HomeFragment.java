package com.example.qicapplication.navigationfragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.qicapplication.activities.ProfileActivity;
import com.example.qicapplication.R;
import com.example.qicapplication.RecycleAdapter;
import com.example.qicapplication.ViewRequests;
import com.example.qicapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint({"ClickableViewAccessibility", "NotifyDataSetChanged"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView2= binding.recyclerView;

        String [] title = getResources().getStringArray(R.array.requests);
        String [] updatedAt = getResources().getStringArray(R.array.update_time);
        String [] number = getResources().getStringArray(R.array.numbers);

        TypedArray requestImageResources = getResources().obtainTypedArray(R.array.request_images);

        List<ViewRequests> items = new ArrayList<>();


        for(int i=0; i<title.length; i++)
        {
            int imageResId = requestImageResources.getResourceId(i,0);
            items.add(new ViewRequests(imageResId,title[i],updatedAt[i],number[i]));
        }

        requestImageResources.recycle();

        recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView2.setAdapter(new RecycleAdapter(requireContext(), items));
        recyclerView2.getAdapter().notifyDataSetChanged();

        recyclerView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        binding.profileButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openProfilePage();
                return false;
            }
        });

        binding.profileImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openProfilePage();
                return false;
            }
        });

    }

    public void openProfilePage()
    {
        Intent intent = new Intent(requireContext(), ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}