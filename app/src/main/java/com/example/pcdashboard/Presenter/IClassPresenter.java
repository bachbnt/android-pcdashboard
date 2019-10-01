package com.example.pcdashboard.Presenter;

import com.example.pcdashboard.Model.ClassPost;

import java.util.ArrayList;

public interface IClassPresenter {
    void onRequest();
    void onResponse(ArrayList<ClassPost> classPosts);
}
