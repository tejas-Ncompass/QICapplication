package com.example.qicapplication.view.Approval;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.ActivityDocumentsBinding;
import com.example.qicapplication.viewmodel.Approvals.LeaveFormatViewModel;

public class DocumentsActivity extends AppCompatActivity {

    private ActivityDocumentsBinding binding;
    private LeaveFormatViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityDocumentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LeaveFormatViewModel.class);

        setSupportActionBar(binding.documnetsToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.medicalCertificateLink.setOnClickListener(v -> viewModel.onAttachmentsClicked());

        viewModel.getNavigationAction().observe(this, event -> {
            if (event == LeaveFormatViewModel.NavigationEvent.ATTACHMENTS) {
                startActivity(new Intent(this, AttachmentsActivity.class));
                viewModel.clearNavigationAction();
            }
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