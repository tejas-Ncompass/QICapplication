package com.example.qicapplication;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qicapplication.databinding.ActivityNavBarBinding;
import com.example.qicapplication.navigationfragments.ApprovalsFragment;
import com.example.qicapplication.navigationfragments.HomeFragment;
import com.example.qicapplication.navigationfragments.NewsFragment;
import com.google.android.material.navigation.NavigationBarView;

public class NavBarActivity extends AppCompatActivity {

    private ActivityNavBarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavBarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        int color = ContextCompat.getColor(this, R.color.maroon);

        ColorStateList colorStateList = ColorStateList.valueOf(color);

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                binding.viewPager.setVisibility(View.GONE);
                binding.frameLayout.setVisibility(View.VISIBLE);

                int id = menuItem.getItemId();

                if(id == R.id.nav_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                    return true;
                }
                if(id == R.id.nav_approvals){

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ApprovalsFragment()).commit();
                    return true;
                }
                else if(id == R.id.nav_news){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NewsFragment()).commit();
                    return true;
                }

                return false;
            }
        });

        if (savedInstanceState == null) {
            binding.bottomNavigation.setSelectedItemId(R.id.nav_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();

            binding.viewPager.setVisibility(View.GONE);
            binding.frameLayout.setVisibility(View.VISIBLE);
        }

    }
}