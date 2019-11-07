package com.example.pcdashboard.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.R;

import static com.example.pcdashboard.Manager.IScreenManager.CLASS_FRAGMENT_TEACHER;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectClassFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private ImageView ivThird;
    private ImageView ivFourth;

    public SelectClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_class, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        ivThird = view.findViewById(R.id.iv_third_select);
        ivFourth = view.findViewById(R.id.iv_fourth_select);
        ivThird.setOnClickListener(this);
        ivFourth.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_third_select:
                SharedPreferencesUtils.saveClassId(getContext(), "3Y");
                screenManager.openFeatureScreen(CLASS_FRAGMENT_TEACHER);
                break;
            case R.id.iv_fourth_select:
                SharedPreferencesUtils.saveClassId(getContext(), "4Y");
                screenManager.openFeatureScreen(CLASS_FRAGMENT_TEACHER);
                break;
        }
    }
}
