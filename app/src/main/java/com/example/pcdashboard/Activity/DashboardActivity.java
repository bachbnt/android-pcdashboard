package com.example.pcdashboard.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.pcdashboard.Fragment.AccountFragment;
import com.example.pcdashboard.Fragment.ConversationFragment;
import com.example.pcdashboard.Fragment.DashboardFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;

public class DashboardActivity extends AppCompatActivity implements IScreenManager {
    private ScreenManager screenManager;
    private final int DASHBOARD_ID = -1;
    private final int CONVERSATION_ID = 0;
    private final int ACCOUNT_ID = 1;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
    }

    private void initialize() {
        screenManager=ScreenManager.getInstance();
        screenManager.setScreenListener(this);
        viewPager=findViewById(R.id.view_pager_dashboard);
        pagerAdapter=new com.example.pcdashboard.Adapter.PagerAdapter(getSupportFragmentManager(),screenManager);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void openLoginScreen(int screenId) {
        //NULL
    }

    @Override
    public Fragment openDashboardScreen(int screenId) {
        Fragment fragment=null;
        switch (screenId) {
            case CONVERSATION_ID:
                fragment= new ConversationFragment();
                break;
            case ACCOUNT_ID:
                fragment= new AccountFragment();
                break;
        }
        return fragment;
    }
}
