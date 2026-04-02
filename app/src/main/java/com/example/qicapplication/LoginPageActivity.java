package com.example.qicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qicapplication.databinding.ActivityLoginPageBinding;
import com.google.android.material.textfield.TextInputEditText;

public class LoginPageActivity extends AppCompatActivity {

    private ActivityLoginPageBinding binding;
    private TextInputEditText emailText, passwordText;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailText = binding.edittextEmailId;
        passwordText = binding.edittextPassword;
        loginBtn = binding.loginButton;

        loginBtn.setEnabled(false);
        loginBtn.setAlpha(0.5f);

        emailText.addTextChangedListener(loginFieldsTextWatcher);
        passwordText.addTextChangedListener(loginFieldsTextWatcher);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              loginIntoApp();
            }
        });

    }

    public void loginIntoApp()
    {
        String email= emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            Toast.makeText(this, "Invalid Email Format!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<8){
            Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(LoginPageActivity.this, NavBarActivity.class);
        startActivity(intent);
        finish();

    }

    private final TextWatcher loginFieldsTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String email= emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();

            if(!email.isEmpty() && !password.isEmpty())
            {
                loginBtn.setEnabled(true);
                loginBtn.setAlpha(1.0f);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}