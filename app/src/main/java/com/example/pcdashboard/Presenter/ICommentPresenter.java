package com.example.pcdashboard.Presenter;

import com.example.pcdashboard.Model.PostComment;

import java.util.ArrayList;

public interface ICommentPresenter {
    void onRequest(String postId);
    void onResponse(ArrayList<PostComment> postComments);
}
