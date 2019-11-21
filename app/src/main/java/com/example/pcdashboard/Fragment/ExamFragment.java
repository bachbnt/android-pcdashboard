package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.ExamAdapter;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.Exam;
import com.example.pcdashboard.Presenter.ExamPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IExamView;

import java.util.ArrayList;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_ACCOUNT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamFragment extends Fragment implements View.OnClickListener, IExamView {
    private ScreenManager screenManager;
    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private ExamAdapter examAdapter;
    private ExamPresenter presenter;


    public ExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setExamView(this);
        presenter.addExamListener();
        presenter.onRequestDatabase();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setExamView(null);
        presenter.removeExamListener();
        super.onPause();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter = new ExamPresenter(getContext());
        examAdapter = new ExamAdapter(getContext(), new ArrayList<Exam>());
        recyclerView = view.findViewById(R.id.recycler_view_exam);
        recyclerView.setAdapter(examAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_right_to_left);
        recyclerView.setLayoutAnimation(animation);
        ibBack = view.findViewById(R.id.ib_back_exam);
        ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_exam:
                ibBack.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                SharedPreferencesUtils.clearNotificationTitle(getContext());
                SharedPreferencesUtils.saveTabId(getContext(), TAB_ACCOUNT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
        }
    }

    @Override
    public void onSuccess(ArrayList<Exam> exams) {
        examAdapter.update(exams);
        examAdapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(), "Tải lịch thi thất bại", CustomToast.LENGTH_SHORT, CustomToast.FAILURE).show();
    }
}
