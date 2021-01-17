package com.example.petshop.Adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.petshop.Class.Category;
import com.example.petshop.Fragment.AccountFragment;
import com.example.petshop.Fragment.CategoryFragment;
import com.example.petshop.Fragment.HomeFragment;
import com.example.petshop.Fragment.SearchViewFragment;
import com.example.petshop.R;

public class NavigationAdapter extends FragmentStateAdapter {
    public NavigationAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }




    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CategoryFragment();
            default:
                return new AccountFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
