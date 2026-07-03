package com.example.qicapplication.view.Approval;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.ActivityLeaveFormatBinding;
import com.example.qicapplication.viewmodel.Approvals.LeaveFormatViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LeaveFormatActivity extends AppCompatActivity {

    private ActivityLeaveFormatBinding binding;
    private LeaveFormatViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLeaveFormatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LeaveFormatViewModel.class);

        setSupportActionBar(binding.leaveFormatToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.approversFab.setOnClickListener(v -> viewModel.onApproversClicked());
        binding.documentsFab.setOnClickListener(v -> viewModel.onDocumentsClicked());
        binding.acceptFab.setOnClickListener(v -> showRemarksDialog());
        binding.declineFab.setOnClickListener(v -> showBottomDialog());

        viewModel.getNavigationAction().observe(this, event -> {
            if (event == null) return;

            switch (event) {
                case APPROVERS:
                    startActivity(new Intent(this, ApproversActivity.class));
                    break;
                case DOCUMENTS:
                    startActivity(new Intent(this, DocumentsActivity.class));
                    break;
                case CLOSE:
                    finish();
                    break;
            }
            viewModel.clearNavigationAction();
        });
    }

    private void showRemarksDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.remarks_dialog_box, findViewById(R.id.dialog_box), false);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        view.findViewById(R.id.close_icon).setOnClickListener(v -> alertDialog.dismiss());
        view.findViewById(R.id.submit_button).setOnClickListener(v -> {
            alertDialog.dismiss();
            viewModel.onSubmitAction();
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    private void showBottomDialog() {
        BottomSheetDialog sheetDialog = new BottomSheetDialog(this, R.style.BottomSheetStyle);
        View sheetView = LayoutInflater.from(this).inflate(R.layout.bottom_dialog_sheet, findViewById(R.id.bottom_dialog), false);

        sheetView.findViewById(R.id.close_bottom_dialog).setOnClickListener(v -> sheetDialog.dismiss());
        sheetView.findViewById(R.id.decline_submit_button).setOnClickListener(v -> {
            sheetDialog.dismiss();
            viewModel.onSubmitAction();
        });

        sheetDialog.setContentView(sheetView);
        sheetDialog.show();
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