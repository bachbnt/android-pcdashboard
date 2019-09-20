package com.example.pcdashboard.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.pcdashboard.Manager.ScreenManager;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private final int NUM_PAGES=4;
    private String title[]={"Tin tức","Lớp học","Trò chuyện","Tài khoản"};
    private ScreenManager screenManager;
    public PagerAdapter(FragmentManager fm, ScreenManager screenManager) {
        super(fm);
        this.screenManager=screenManager;
    }

    @Override
    public Fragment getItem(int position) {
        return screenManager.openDashboardScreen(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
