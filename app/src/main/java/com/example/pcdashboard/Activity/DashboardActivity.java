package com.example.pcdashboard.Activity;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
    private final int DASHBOARD_ID = -1;
    private final int INFORMATION_ID = 0;
    private final int CLASSROOM_ID = 1;
    private final int CONVERSATION_ID = 2;
    private final int ACCOUNT_ID = 3;
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
        screenManager.setScreenListener(this);
        viewPager = findViewById(R.id.view_pager_dashboard);
        pagerAdapter = new com.example.pcdashboard.Adapter.PagerAdapter(getSupportFragmentManager(), screenManager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout=findViewById(R.id.tab_layout_dashboard);
        tabLayout.setupWithViewPager(viewPager);
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
        }
        return fragment;
    }
}
