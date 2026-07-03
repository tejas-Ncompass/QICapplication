package com.example.qicapplication.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.ActivityLoginPageBinding;
import com.example.qicapplication.viewmodel.Login.LoginViewModel;

import androidx.lifecycle.ViewModelProvider;


public class LoginPageActivity extends AppCompatActivity {

    private ActivityLoginPageBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(" ");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets ime = insets.getInsets(WindowInsetsCompat.Type.ime());

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, ime.bottom);

            if (insets.isVisible(WindowInsetsCompat.Type.ime())) {
                binding.getRoot().postDelayed(() ->
                        binding.loginScrollView.smoothScrollTo(0, binding.loginButton.getBottom()), 100);
            }
            return insets;
        });

        binding.loginButton.setOnClickListener(v -> viewModel.attemptLogin());

        TextWatcher inputWatcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                String email = binding.edittextEmailId.getText().toString();
                String pass = binding.edittextPassword.getText().toString();
                viewModel.onInputChanged(email, pass);
            }
        };
        binding.edittextEmailId.addTextChangedListener(inputWatcher);
        binding.edittextPassword.addTextChangedListener(inputWatcher);

        viewModel.getIsLoginButtonEnabled().observe(this, isEnabled -> {
            binding.loginButton.setEnabled(isEnabled);
            binding.loginButton.setAlpha(isEnabled ? 1.0f : 0.5f);
        });

        viewModel.getLoginResult().observe(this, state -> {
            if (state == null) return;

            switch (state) {
                case INVALID_EMAIL:
                    Toast.makeText(this, "Invalid Email Format!", Toast.LENGTH_SHORT).show();
                    break;
                case INVALID_PASSWORD:
                    Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, SetPinActivity.class));
                    finish();
                    break;
            }
            viewModel.clearLoginResult();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}