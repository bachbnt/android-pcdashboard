package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.example.pcdashboard.Manager.DatabaseHelper;
import com.example.pcdashboard.Model.Exam;
import com.example.pcdashboard.Services.StudyService;
import com.example.pcdashboard.View.IExamView;

import java.util.ArrayList;

interface IExamPresenter {

    void onRequestDatabase();
    void onRequestServer();

    void onResponse(ArrayList<Exam> exams);
}

public class ExamPresenter implements IExamPresenter, StudyService.ExamListener {
    class ExamTask extends AsyncTask<String, Void, ArrayList<Exam>> {

        @Override
        protected ArrayList<Exam> doInBackground(String... strings) {
            ArrayList<Exam> exams = databaseHelper.loadExams();
            return exams;
        }

        @Override
        protected void onPostExecute(ArrayList<Exam> exams) {
            super.onPostExecute(exams);
            if (exams != null) {
                onResponse(exams);
            }
            onRequestServer();
        }
    }
    private Context context;
    private IExamView view;
    private StudyService studyService;
    private DatabaseHelper databaseHelper;

    public ExamPresenter(Context context) {
        this.context = context;
        studyService = StudyService.getInstance(context);
        databaseHelper=DatabaseHelper.getInstance(context);
    }

    public void setExamView(IExamView iExamView) {
        this.view = iExamView;
    }

    public void addExamListener() {
        studyService.setExamListener(this);
    }

    public void removeExamListener() {
        studyService.setExamListener(null);
    }

    @Override
    public void onRequestDatabase() {
        ExamTask examTask=new ExamTask();
        examTask.execute();
    }

    @Override
    public void onRequestServer() {
        studyService.getExams();
    }

    @Override
    public void onResponse(ArrayList<Exam> exams) {
        view.onSuccess(exams);
    }

    @Override
    public void onSuccess(ArrayList<Exam> exams) {
        onResponse(exams);
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }
}
