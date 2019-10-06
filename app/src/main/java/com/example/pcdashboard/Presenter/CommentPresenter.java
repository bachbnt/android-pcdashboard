package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.API.PostService;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.View.ICommentView;

import java.util.ArrayList;

public class CommentPresenter implements ICommentPresenter,PostService.CommentListener {
    private Context context;
    private ICommentView view;
    private PostService postService;

    public CommentPresenter(Context context) {
        this.context = context;
        postService=PostService.getInstance(context);
    }
    public void setClassView(ICommentView iCommentView){
        this.view=iCommentView;
    }

    public void addCommentListener(){
        postService.setCommentListener(this);
    }

    public void removeCommentListener(){
        postService.setCommentListener(null);
    }
    @Override
    public void onRequest(String postId) {
        postService.getPostComments(postId);
    }

    @Override
    public void onResponse(ArrayList<PostComment> postComments) {
        view.onUpdate(postComments);
    }

    @Override
    public void onSuccess(ArrayList<PostComment> postComments) {
        onResponse(postComments);
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }
}
