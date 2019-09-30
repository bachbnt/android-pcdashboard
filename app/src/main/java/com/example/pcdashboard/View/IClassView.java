package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.ClassPost;

import java.util.ArrayList;

public interface IClassView {
    void onLoading();
    void onUpdate(ArrayList<ClassPost> classPosts);
    void onFailure();
}
