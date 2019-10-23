package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Model.Schedule;
import com.example.pcdashboard.Services.StudyService;
import com.example.pcdashboard.View.IScheduleView;

import java.util.ArrayList;

interface ISchedulePresenter{
    void onInit();
    void onRequest();
    void onResponse(ArrayList<Schedule> schedules);
}
public class SchedulePresenter implements StudyService.ScheduleListener,ISchedulePresenter {
    private Context context;
    private IScheduleView view;
    private StudyService studyService;
    public SchedulePresenter(Context context) {
        this.context = context;
        studyService=StudyService.getInstance(context);
    }
    public void setScheduleView(IScheduleView iScheduleView){
        this.view=iScheduleView;
    }

    public void addScheduleListener(){
        studyService.setScheduleListener(this);
    }
    public void removeScheduleListener(){
        studyService.setScheduleListener(null);
    }

    @Override
    public void onSuccess(ArrayList<Schedule> schedules) {
        onResponse(schedules);
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onRequest() {
        studyService.getSchedules();
    }

    @Override
    public void onResponse(ArrayList<Schedule> schedules) {
        view.onSuccess(schedules);
    }
}
