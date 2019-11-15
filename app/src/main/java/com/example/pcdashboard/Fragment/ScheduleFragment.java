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

import com.example.pcdashboard.Adapter.ScheduleAdapter;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.Schedule;
import com.example.pcdashboard.Model.Subject;
import com.example.pcdashboard.Presenter.SchedulePresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IScheduleView;

import java.util.ArrayList;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_ACCOUNT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment implements View.OnClickListener, IScheduleView, ScheduleAdapter.OnItemClickListener {
    private ScreenManager screenManager;
    private ImageButton ibBack, ibReload, ibSave;
    private SchedulePresenter presenter;
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;
    private ArrayList<Schedule> schedules;
    private ArrayList<Subject> subjects;


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

    @Override
    public void onResume() {
        presenter.setScheduleView(this);
        presenter.addScheduleListener();
        if (SharedPreferencesUtils.loadFirstRequestSchedule(getContext())) {
            presenter.onRequestServer();
            presenter.changeFirstRequest();
        } else presenter.onRequestDatabase();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setScheduleView(this);
        presenter.removeScheduleListener();
        super.onPause();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter = new SchedulePresenter(getContext());
        ibBack = view.findViewById(R.id.ib_back_schedule);
        ibReload = view.findViewById(R.id.ib_reload_schedule);
        ibSave = view.findViewById(R.id.ib_save_schedule);
        scheduleAdapter = new ScheduleAdapter(getContext(), new ArrayList<Schedule>(), this);
        recyclerView = view.findViewById(R.id.recycler_view_schedule);
        recyclerView.setAdapter(scheduleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_left_to_right);
        recyclerView.setLayoutAnimation(animation);
        ibBack.setOnClickListener(this);
        ibReload.setOnClickListener(this);
        ibSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_schedule:
                SharedPreferencesUtils.saveTabId(getContext(), TAB_ACCOUNT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.ib_reload_schedule:
                presenter.onRequestServer();
                CustomToast.makeText(getContext(), "Đã tải", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS).show();
                break;
            case R.id.ib_save_schedule:
                presenter.onCustomDatabase(schedules);
                CustomToast.makeText(getContext(), "Đã lưu", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS).show();
                presenter.onRequestDatabase();
                break;
        }
    }

    @Override
    public void onSuccess(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
        scheduleAdapter.update(schedules);
        scheduleAdapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(), "Tải thời khóa biểu thất bại", CustomToast.LENGTH_SHORT, CustomToast.FAILURE).show();
    }

    @Override
    public void onAdd(Schedule schedule, int position) {
        schedules.get(position).getSubjects().add(new Subject(null, null, null, schedule.getDay()));
        scheduleAdapter.update(schedules);
        scheduleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEdit(Subject subject, int position, String name, String time, String teacher) {
        for (Schedule schedule : schedules) {
            if (schedule.getDay().equals(subject.getDay())) {
                schedule.getSubjects().get(position).setName(name);
                schedule.getSubjects().get(position).setTime(time);
                schedule.getSubjects().get(position).setTeacher(teacher);
            }
        }
        scheduleAdapter.update(schedules);
        scheduleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDelete(Subject subject) {
        for (Schedule schedule : schedules) {
            if (schedule.getDay().equals(subject.getDay()))
                schedule.getSubjects().remove(subject);
        }
        scheduleAdapter.update(schedules);
        scheduleAdapter.notifyDataSetChanged();
    }

}
