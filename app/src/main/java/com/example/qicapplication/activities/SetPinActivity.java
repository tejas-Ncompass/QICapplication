package com.example.qicapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chaos.view.PinView;
import com.example.qicapplication.databinding.ActivitySetPinBinding;

public class SetPinActivity extends AppCompatActivity {

    private ActivitySetPinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySetPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.main , (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button setButton = binding.setPinButton;
        TextView skip = binding.skip;

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNavbarPage();

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetPinActivity.this, NavBarActivity.class);
                startActivity(intent);
            }
        });

    }
    public void openNavbarPage()
    {
        PinView pinView = binding.pin;
        String enteredPin = pinView.getText().toString();

        if (enteredPin.length() != 4) {
            Toast.makeText(this, "PIN is required ", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(SetPinActivity.this, NavBarActivity.class);
        startActivity(intent);

    }
}

