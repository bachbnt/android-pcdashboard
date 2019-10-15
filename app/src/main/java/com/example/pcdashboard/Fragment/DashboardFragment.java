package com.example.pcdashboard.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcdashboard.Adapter.PagerAdapter;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    private ScreenManager screenManager;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private int tabId=0;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        tabId= SharedPreferencesUtil.loadTabId(getContext());
        viewPager.setCurrentItem(tabId);
        super.onResume();
    }

    @Override
    public void onStop() {
        SharedPreferencesUtil.clearTabId(getContext());
        super.onStop();
    }

    private void initialize(View view){
        screenManager=ScreenManager.getInstance();
        viewPager = view.findViewById(R.id.view_pager_dashboard);
        pagerAdapter = new PagerAdapter(getFragmentManager(), getContext(), screenManager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(tabId);
        tabLayout = view.findViewById(R.id.tab_layout_dashboard);
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
}