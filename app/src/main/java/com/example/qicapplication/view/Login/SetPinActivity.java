package com.example.qicapplication.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qicapplication.view.Dashboard.NavBarActivity;
import com.example.qicapplication.databinding.ActivitySetPinBinding;
import com.example.qicapplication.viewmodel.Login.SetPinViewModel;

import androidx.lifecycle.ViewModelProvider;

public class SetPinActivity extends AppCompatActivity {

    private ActivitySetPinBinding binding;
    private SetPinViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySetPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SetPinViewModel.class);

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets ime = insets.getInsets(WindowInsetsCompat.Type.ime());

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, ime.bottom);

            if (insets.isVisible(WindowInsetsCompat.Type.ime())) {
                binding.getRoot().postDelayed(() ->
                        binding.setpinScrollView.smoothScrollTo(0, binding.setPinButton.getBottom()), 100);
            }
            return insets;
        });

        binding.setPinButton.setOnClickListener(v -> {
            String enteredPin = binding.pin.getText().toString();
            viewModel.validatePin(enteredPin);
        });

        binding.skip.setOnClickListener(v -> viewModel.skipPin());

        viewModel.getPinSuccessStatus().observe(this, state -> {
            if (state == null) return;

            switch (state) {
                case INVALID:
                    Toast.makeText(this, "PIN is required", Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                case SKIP:
                    startActivity(new Intent(this, NavBarActivity.class));
                    finish();
                    break;
            }
            viewModel.clearPinEvent();
        });
    }
}
