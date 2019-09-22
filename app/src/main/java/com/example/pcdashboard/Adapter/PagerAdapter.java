package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private final int NUM_PAGES = 4;
    private String title[] = {"Bộ môn", "Lớp học", "Tin nhắn", "Tài khoản"};
    private int icon[] = {R.drawable.ic_home_sky_blue_24dp, R.drawable.ic_school_sky_blue_24dp, R.drawable.ic_chat_sky_blue_24dp, R.drawable.ic_account_circle_sky_blue_24dp};
    private int selectedIcon[] = {R.drawable.ic_home_orange_24dp, R.drawable.ic_school_orange_24dp, R.drawable.ic_chat_orange_24dp, R.drawable.ic_account_circle_orange_24dp};
    private Context context;
    private ScreenManager screenManager;


    public View initTab(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout_item, null);
        TextView tvTitle = view.findViewById(R.id.tv_title_tablayout);
        ImageView ivIcon = view.findViewById(R.id.iv_icon_tablayout);
        tvTitle.setText(title[position]);
        tvTitle.setTextColor(context.getResources().getColor(R.color.colorSkyBlue));
        ivIcon.setImageResource(icon[position]);
        return view;
    }

    public View selectTab(View view, int position, boolean isSelected) {
        TextView tvTitle = view.findViewById(R.id.tv_title_tablayout);
        ImageView ivIcon = view.findViewById(R.id.iv_icon_tablayout);
        if (isSelected) {
            tvTitle.setTextColor(context.getResources().getColor(R.color.colorOrange));
            ivIcon.setImageResource(selectedIcon[position]);
        } else {
            tvTitle.setTextColor(context.getResources().getColor(R.color.colorSkyBlue));
            ivIcon.setImageResource(icon[position]);
        }
        return view;
    }

    public PagerAdapter(FragmentManager fm, Context context, ScreenManager screenManager) {
        super(fm);
        this.context = context;
        this.screenManager = screenManager;
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
