package com.example.pcdashboard.Services;

import android.content.Context;

import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.Exam;
import com.example.pcdashboard.Model.Schedule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudyService {
    private static StudyService studyService;
    private static IStudyService iStudyService;
    private Context context;
    private ScheduleListener scheduleListener;
    private ExamListener examListener;

    public interface ScheduleListener {
        void onSuccess(ArrayList<Schedule> schedules);

        void onFailure();
    }

    public interface ExamListener {
        void onSuccess(ArrayList<Exam> exams);

        void onFailure();
    }

    public static StudyService getInstance(Context context) {
        if (studyService == null)
            studyService = new StudyService(context);
        return studyService;
    }

    private StudyService(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWebService.urlServer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iStudyService = retrofit.create(IStudyService.class);
    }

    public void setScheduleListener(ScheduleListener scheduleListener) {
        this.scheduleListener = scheduleListener;
    }

    public void setExamListener(ExamListener examListener) {
        this.examListener = examListener;
    }

    public void getSchedule() {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        String classId = SharedPreferencesUtils.loadSelf(context).getClassId();
        Call<ArrayList<Schedule>> call = iStudyService.getSchedule(token, classId);
        call.enqueue(new Callback<ArrayList<Schedule>>() {
            @Override
            public void onResponse(Call<ArrayList<Schedule>> call, Response<ArrayList<Schedule>> response) {
                final ArrayList<Schedule> schedules = response.body();
                if (schedules != null)
                    scheduleListener.onSuccess(schedules);
                else scheduleListener.onFailure();
            }

            @Override
            public void onFailure(Call<ArrayList<Schedule>> call, Throwable t) {
                scheduleListener.onFailure();
            }
        });
    }
    public void getExam() {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<ArrayList<Exam>> call = iStudyService.getExam(token);
        call.enqueue(new Callback<ArrayList<Exam>>() {
            @Override
            public void onResponse(Call<ArrayList<Exam>> call, Response<ArrayList<Exam>> response) {
                final ArrayList<Exam> exams = response.body();
                if (exams != null)
                    examListener.onSuccess(exams);
                else examListener.onFailure();
            }

            @Override
            public void onFailure(Call<ArrayList<Exam>> call, Throwable t) {
                examListener.onFailure();
            }
        });
    }
}
