package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.ScheduleAdapter;
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
public class ScheduleFragment extends Fragment implements View.OnClickListener, IScheduleView {
    private ScreenManager screenManager;
    private ImageButton ibBack,ibReload,ibSave;
    private SchedulePresenter presenter;
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;


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
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setScheduleView(this);
        presenter.removeScheduleListener();
        super.onPause();
    }

    private void initialize(View view) {
        screenManager=ScreenManager.getInstance();
        presenter=new SchedulePresenter(getContext());
        ibBack=view.findViewById(R.id.ib_back_schedule);
        ibReload=view.findViewById(R.id.ib_reload_schedule);
        ibSave=view.findViewById(R.id.ib_save_schedule);
        scheduleAdapter=new ScheduleAdapter(getContext(),list());
        recyclerView=view.findViewById(R.id.recycler_view_schedule);
        recyclerView.setAdapter(scheduleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ibBack.setOnClickListener(this);
        ibReload.setOnClickListener(this);
        ibSave.setOnClickListener(this);
    }

    private ArrayList<Schedule> list(){
        ArrayList<Schedule> schedules=new ArrayList<>();
        ArrayList<Subject> subjects=new ArrayList<>();
        subjects.add(new Subject("Lap trinh di dong","8:00-10:10","Nguyen Anh Thu"));
        subjects.add(new Subject("Lap trinh di dong","8:00-10:10","Nguyen Anh Thu"));
        subjects.add(new Subject("Lap trinh di dong","8:00-10:10","Nguyen Anh Thu"));
        subjects.add(new Subject("Lap trinh di dong","8:00-10:10","Nguyen Anh Thu"));
        schedules.add(new Schedule("Thu 2",subjects));
        schedules.add(new Schedule("Thu 3",subjects));
        schedules.add(new Schedule("Thu 4",subjects));
        schedules.add(new Schedule("Thu 5",subjects));
        schedules.add(new Schedule("Thu 6",subjects));
        schedules.add(new Schedule("Thu 7",subjects));
        schedules.add(new Schedule("Chu nhat",subjects));
        return schedules;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back_schedule:
                SharedPreferencesUtils.saveTabId(getContext(),TAB_ACCOUNT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.ib_reload_schedule:
//                presenter.onRequest();
                break;
            case R.id.ib_save_schedule:
                break;
        }
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onSuccess(ArrayList<Schedule> schedules) {
    }

    @Override
    public void onFailure() {

    }
}
