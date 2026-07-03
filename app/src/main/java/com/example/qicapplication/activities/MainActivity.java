package com.example.qicapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qicapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);

//        ViewCompat.setOnApplyWindowInsetsListener( binding.main , (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this, LoginPageActivity.class);
                startActivity(intent);
                finish();
            }
        };

        handler.postDelayed(runnable, 2000);

        startProgress();
    }

    private void startProgress() {
        new Thread(()->{
            while(progress<100){
                progress++;
                handler.post(()->{
                    binding.progressBar.setProgress(progress);
                });
                try{
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}