package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.Schedule;

import java.util.ArrayList;

public interface IScheduleView {
    void onInit();
    void onSuccess(ArrayList<Schedule> schedules);
    void onFailure();
}
