package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Model.User;

import java.util.ArrayList;

public interface IClassView {
    void onInit(User self);
    void onSuccessDatabase(ArrayList<ClassPost>classPosts);
    void onSuccessServer(ArrayList<ClassPost> classPosts);
    void onFailure();
    void onDeleteSuccess();
    void onDeleteFailure();
}
