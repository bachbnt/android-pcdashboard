package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.OnSwipeTouchListener;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.R;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_ACCOUNT;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private ImageButton ibBack;
    private ImageView ivImage;
    private int currentImage;
    private Animation animation;


    public GuideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        currentImage = 1;
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        ibBack = view.findViewById(R.id.ib_back_guide);
        ivImage = view.findViewById(R.id.iv_image_guide);
        ivImage.setImageResource(R.drawable.guide_teacher_1);
        ibBack.setOnClickListener(this);
        ivImage.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                switch (currentImage) {
                    case 1:
                        ivImage.startAnimation(animation);
                        ivImage.setImageResource(R.drawable.guide_teacher_2);
                        currentImage++;
                        break;
                    case 2:
                        ivImage.startAnimation(animation);
                        if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER"))
                            ivImage.setImageResource(R.drawable.guide_teacher_3);
                        else ivImage.setImageResource(R.drawable.guide_student_3);
                        currentImage++;
                        break;
                    case 3:
                        ivImage.startAnimation(animation);
                        if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER"))
                            ivImage.setImageResource(R.drawable.guide_teacher_4);
                        else ivImage.setImageResource(R.drawable.guide_student_4);
                        currentImage++;
                        break;
                    case 4:
                        ivImage.startAnimation(animation);
                        if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER"))
                            ivImage.setImageResource(R.drawable.guide_teacher_5);
                        else ivImage.setImageResource(R.drawable.guide_student_5);
                        currentImage++;
                        break;
                }
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                switch (currentImage) {
                    case 2:
                        ivImage.startAnimation(animation);
                        ivImage.setImageResource(R.drawable.guide_teacher_1);
                        currentImage--;
                        break;
                    case 3:
                        ivImage.startAnimation(animation);
                        ivImage.setImageResource(R.drawable.guide_teacher_2);
                        currentImage--;
                        break;
                    case 4:
                        ivImage.startAnimation(animation);
                        if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER"))
                            ivImage.setImageResource(R.drawable.guide_teacher_3);
                        else ivImage.setImageResource(R.drawable.guide_student_3);
                        currentImage--;
                        break;
                    case 5:
                        ivImage.startAnimation(animation);
                        if (SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER"))
                            ivImage.setImageResource(R.drawable.guide_teacher_4);
                        else ivImage.setImageResource(R.drawable.guide_student_4);
                        currentImage--;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_guide:
                ibBack.startAnimation(animation);
                SharedPreferencesUtils.saveTabId(getContext(), TAB_ACCOUNT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
        }
    }
}
