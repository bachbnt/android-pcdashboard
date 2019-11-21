package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.R;

import static com.example.pcdashboard.Manager.IScreenManager.CHAT_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.USER_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private ImageView ivOne;
    private ImageView ivTwo;
    private ImageView ivThree;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        ivOne = view.findViewById(R.id.iv_one_contact);
        ivTwo = view.findViewById(R.id.iv_two_contact);
        ivThree = view.findViewById(R.id.iv_three_contact);
        if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER"))
        {
         ivOne.setImageResource(R.drawable.teachers_information);
         ivTwo.setImageResource(R.drawable.students_3rd_information);
         ivThree.setImageResource(R.drawable.students_4th_information);
        }
        ivOne.setOnClickListener(this);
        ivTwo.setOnClickListener(this);
        ivThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_one_contact:
                ivOne.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER")){
                    SharedPreferencesUtils.saveClassId(getContext(),SharedPreferencesUtils.loadSelf(getContext()).getClassId());
                    screenManager.openFeatureScreen(USER_FRAGMENT);
                }
                else
                    screenManager.openFeatureScreen(CHAT_FRAGMENT);
                break;
            case R.id.iv_two_contact:
                ivTwo.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER")){
                    screenManager.openFeatureScreen(USER_FRAGMENT);
                    SharedPreferencesUtils.saveClassId(getContext(),"3Y");
                }
                else{
                    SharedPreferencesUtils.saveClassId(getContext(),SharedPreferencesUtils.loadSelf(getContext()).getClassId());
                    screenManager.openFeatureScreen(USER_FRAGMENT);
                }
                break;
            case R.id.iv_three_contact:
                ivThree.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER")){
                    SharedPreferencesUtils.saveClassId(getContext(), "4Y");
                    screenManager.openFeatureScreen(USER_FRAGMENT);
                }
                else {
                    SharedPreferencesUtils.saveClassId(getContext(), "GV");
                    screenManager.openFeatureScreen(USER_FRAGMENT);
                }
                break;
        }
    }
}
