package com.example.qicapplication.view.Approval;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.ActivityApprovalBinding;
import com.example.qicapplication.viewmodel.Approvals.ApprovalPageViewModel;
import com.google.android.material.button.MaterialButtonToggleGroup;

import org.jspecify.annotations.NonNull;

public class ApprovalActivity extends AppCompatActivity {

    private ActivityApprovalBinding binding;
    private ApprovalPageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityApprovalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ApprovalPageViewModel.class);

        setSupportActionBar(binding.approvalToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupToggleGroup();
        observeViewModel();
    }

    private void setupToggleGroup() {
        MaterialButtonToggleGroup toggleBtnGroup = binding.toggleButton;
        toggleBtnGroup.setSingleSelection(true);
        toggleBtnGroup.setSelectionRequired(true);

        toggleBtnGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                viewModel.selectTab(checkedId);
            }
        });
    }

    private void observeViewModel() {
        viewModel.getCurrentTabState().observe(this, state -> {
            if (state == null) return;
            switch (state) {
                case LEAVE_REQUEST:
                    binding.approvalTitle.setText("Approval");
                    binding.toggleButton.check(R.id.leave_requestes_button);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.approval_fragment_container, LeaveRequestFragment.class, null)
                            .commit();
                    break;
                case HISTORY:
                    binding.approvalTitle.setText("Approval History");
                    binding.toggleButton.check(R.id.history_button);
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.approval_fragment_container, HistoryFragment.class, null)
                            .commit();
                    break;
            }
        });
    }

    // back arrow
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}