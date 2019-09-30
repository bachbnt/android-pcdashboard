package com.example.pcdashboard.Presenter;

import com.example.pcdashboard.Model.DepartmentPost;

import java.util.ArrayList;

public interface IDepartmentPresenter {
    void onRequest();
    void onResponse(ArrayList<DepartmentPost> departmentPosts);
}
