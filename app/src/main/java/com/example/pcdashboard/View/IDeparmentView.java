package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.DepartmentPost;

import java.util.ArrayList;

public interface IDeparmentView {
    void onSuccessDatabase(ArrayList<DepartmentPost> departmentPosts);
    void onSuccessServer(ArrayList<DepartmentPost> departmentPosts);
    void onFailure();
}
