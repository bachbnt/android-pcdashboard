package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.R;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_ACCOUNT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private ImageButton ibBack,ibReload,ibSave;


    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        screenManager=ScreenManager.getInstance();
        ibBack=view.findViewById(R.id.ib_back_schedule);
        ibReload=view.findViewById(R.id.ib_reload_schedule);
        ibSave=view.findViewById(R.id.ib_save_schedule);
        ibBack.setOnClickListener(this);
        ibReload.setOnClickListener(this);
        ibSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back_schedule:
                SharedPreferencesUtil.saveTabId(getContext(),TAB_ACCOUNT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.ib_reload_schedule:
                break;
            case R.id.ib_save_schedule:
                break;
        }
    }
}
