package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Model.Exam;
import com.example.pcdashboard.Services.StudyService;
import com.example.pcdashboard.View.IExamView;

import java.util.ArrayList;

interface IExamPresenter {
    void onRequest();

    void onResponse(ArrayList<Exam> exams);
}

public class ExamPresenter implements IExamPresenter, StudyService.ExamListener {
    private Context context;
    private IExamView view;
    private StudyService studyService;

    public ExamPresenter(Context context) {
        this.context = context;
        studyService = StudyService.getInstance(context);

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
    public void onRequest() {
        studyService.getExam();
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
