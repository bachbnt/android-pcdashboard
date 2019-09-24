package com.example.pcdashboard.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pcdashboard.Adapter.PagerAdapter;
import com.example.pcdashboard.Fragment.AccountFragment;
import com.example.pcdashboard.Fragment.ClassroomFragment;
import com.example.pcdashboard.Fragment.ConversationFragment;
import com.example.pcdashboard.Fragment.InformationFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity implements IScreenManager {
    private ScreenManager screenManager;
    private final int INFORMATION_ID = 0;
    private final int CLASSROOM_ID = 1;
    private final int CONVERSATION_ID = 2;
    private final int ACCOUNT_ID = 3;
    private final int LOGIN_ID = 4;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
    }

    private void initialize() {
        screenManager = ScreenManager.getInstance();
        screenManager.setScreenManager(this);
        viewPager = findViewById(R.id.view_pager_dashboard);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this, screenManager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        tabLayout = findViewById(R.id.tab_layout_dashboard);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(pagerAdapter.initTab(i));
            tabLayout.getTabAt(0).setCustomView(pagerAdapter.selectTab(tabLayout.getTabAt(0).getCustomView(), tabLayout.getTabAt(0).getPosition(), true));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setCustomView(pagerAdapter.selectTab(tab.getCustomView(), tab.getPosition(), true));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(pagerAdapter.selectTab(tab.getCustomView(), tab.getPosition(), false));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void openLoginScreen(int screenId) {
        //NULL
    }

    @Override
    public Fragment openDashboardScreen(int screenId) {
        Fragment fragment = null;
        switch (screenId) {
            case INFORMATION_ID:
                fragment = new InformationFragment();
                break;
            case CLASSROOM_ID:
                fragment = new ClassroomFragment();
                break;
            case CONVERSATION_ID:
                fragment = new ConversationFragment();
                break;
            case ACCOUNT_ID:
                fragment = new AccountFragment();
                break;
            case LOGIN_ID:
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return fragment;
    }
}
