package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.DepartmentPost;

import java.util.ArrayList;

public interface IDeparmentView {
    void onSuccess(ArrayList<DepartmentPost> departmentPosts);
    void onFailure();
}
