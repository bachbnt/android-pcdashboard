package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.PostComment;

import java.util.ArrayList;

public interface ICommentView {
    void onUpdate(ArrayList<PostComment> postComments);
    void onSuccess();
    void onFailure();
}
