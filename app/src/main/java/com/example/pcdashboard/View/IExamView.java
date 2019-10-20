package com.example.pcdashboard.View;


import com.example.pcdashboard.Model.Exam;

import java.util.ArrayList;

public interface IExamView {
    void onInit();
    void onSuccess(ArrayList<Exam> exams);
    void onFailure();
}
