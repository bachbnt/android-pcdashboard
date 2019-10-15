package com.example.pcdashboard.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private ImageButton ibBack;


    public ExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_exam, container, false);
        initialize(view);
        return view;
    }
private void initialize(View view){
        screenManager=ScreenManager.getInstance();
        ibBack=view.findViewById(R.id.ib_back_exam);
        ibBack.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back_exam:
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
        }
    }
}
