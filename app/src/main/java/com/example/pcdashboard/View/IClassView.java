package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.PostComment;

import java.util.ArrayList;

public interface IClassView {
    void onUpdate(ArrayList<ClassPost> classPosts);
    void onFailure();
}
