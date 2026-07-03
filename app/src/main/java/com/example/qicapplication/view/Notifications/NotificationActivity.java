package com.example.qicapplication.view.Notifications;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.qicapplication.R;
import com.example.qicapplication.databinding.ActivityNotificationBinding;
import com.example.qicapplication.view.Notifications.notificationfragments.AllFragment;
import com.example.qicapplication.view.Notifications.notificationfragments.OthersFragment;
import com.example.qicapplication.view.Notifications.notificationfragments.PersonalFragment;
import com.google.android.material.chip.ChipGroup;

public class NotificationActivity extends AppCompatActivity {
    private ActivityNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.notificationsToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.notification_fragment_container, AllFragment.class, null)
                    .commit();
        }

        binding.chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (!checkedIds.isEmpty()) {
                int checkedId = checkedIds.get(0);
                Class<? extends Fragment> targetFragment;

                if (checkedId == R.id.personal_chip) {
                    targetFragment = PersonalFragment.class;
                } else if (checkedId == R.id.others_chip) {
                    targetFragment = OthersFragment.class;
                } else {
                    targetFragment = AllFragment.class;
                }

                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.notification_fragment_container, targetFragment, null)
                        .commit();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
