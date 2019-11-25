package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.example.pcdashboard.Manager.DatabaseHelper;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.Schedule;
import com.example.pcdashboard.Services.StudyService;
import com.example.pcdashboard.View.IScheduleView;

import java.util.ArrayList;

interface ISchedulePresenter{
    void onRequestDatabase();
    void onRequestServer();
    void onResponse(ArrayList<Schedule> schedules);
    void onCustomDatabase(ArrayList<Schedule> schedules);
    void changeFirstRequest();
}
public class SchedulePresenter implements StudyService.ScheduleListener,ISchedulePresenter {
    class ScheduleTask extends AsyncTask<String, Void, ArrayList<Schedule>> {

        @Override
        protected ArrayList<Schedule> doInBackground(String... strings) {
            ArrayList<Schedule> schedules = databaseHelper.loadSchedules();
            return schedules;
        }

        @Override
        protected void onPostExecute(ArrayList<Schedule> schedules) {
            super.onPostExecute(schedules);
            if (schedules != null) {
                onResponse(schedules);
            }
        }
    }
    private Context context;
    private IScheduleView view;
    private StudyService studyService;
    private DatabaseHelper databaseHelper;
    public SchedulePresenter(Context context) {
        this.context = context;
        studyService=StudyService.getInstance(context);
        databaseHelper=DatabaseHelper.getInstance(context);
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
        if(view!=null)
        view.onFailure();
    }

    @Override
    public void onRequestDatabase() {
        ScheduleTask scheduleTask=new ScheduleTask();
        scheduleTask.execute();
    }

    @Override
    public void onRequestServer() {
        studyService.getSchedules();
    }

    @Override
    public void onResponse(ArrayList<Schedule> schedules) {
        if(view!=null)
        view.onSuccess(schedules);
    }

    @Override
    public void onCustomDatabase(ArrayList<Schedule> schedules) {
        databaseHelper.deleteSchedules();
        for(Schedule schedule:schedules)
            databaseHelper.insertSchedule(schedule);
    }

    @Override
    public void changeFirstRequest() {
        SharedPreferencesUtils.saveFirstRequestSchedule(context,false);
    }
}
