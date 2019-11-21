package com.example.pcdashboard.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.pcdashboard.Adapter.PagerAdapter;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Manager.ZoomOutPageTransformer;
import com.example.pcdashboard.R;
import com.google.android.material.tabs.TabLayout;

import static com.example.pcdashboard.Manager.IScreenManager.EXAM_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.WEB_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private ImageView ivAppName;

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
    public void onStart() {
        viewPager.setCurrentItem(SharedPreferencesUtils.loadTabId(getContext()));
        if(SharedPreferencesUtils.loadNotificationTitle(getContext())!=null)
            openScreenFromNotifications();
        super.onStart();
    }

    @Override
    public void onStop() {
        SharedPreferencesUtils.clearTabId(getContext());
        super.onStop();
    }

    private void initialize(View view){
        screenManager=ScreenManager.getInstance();
        ivAppName=view.findViewById(R.id.iv_appname_dashboard);
        ivAppName.setOnClickListener(this);
        viewPager = view.findViewById(R.id.view_pager_dashboard);
        pagerAdapter = new PagerAdapter(getChildFragmentManager(), getContext(), screenManager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
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
                tab.getCustomView().startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.scale_up));
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_appname_dashboard:
                ivAppName.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                screenManager.openFeatureScreen(WEB_FRAGMENT);
                break;
        }
    }
    private void openScreenFromNotifications() {
        switch (SharedPreferencesUtils.loadNotificationTitle(getContext())) {
            case "Lớp học":
                viewPager.setCurrentItem(1);
                break;
            case "Lịch thi":
                viewPager.setCurrentItem(3);
                screenManager.openFeatureScreen(EXAM_FRAGMENT);
                break;
            case "Điểm thi":
                viewPager.setCurrentItem(3);
                screenManager.openFeatureScreen(EXAM_FRAGMENT);
                break;
        }
    }
}