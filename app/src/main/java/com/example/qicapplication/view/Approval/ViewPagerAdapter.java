package com.example.qicapplication.view.Approval;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.qicapplication.view.Approval.approvalhistory.AcceptedFragment;
import com.example.qicapplication.view.Approval.approvalhistory.AllApprovalsFragment;
import com.example.qicapplication.view.Approval.approvalhistory.RejectedFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1: return new AcceptedFragment();
            case 2: return new RejectedFragment();
            default: return new AllApprovalsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}