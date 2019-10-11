package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Services.PostService;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.View.ICommentView;

import java.util.ArrayList;

interface ICommentPresenter {
    void onRequest(String postId);
    void onResponse(ArrayList<PostComment> postComments);
    void onCreate(String content);
    void onEdit(PostComment postComment);
    void onDelete(PostComment postComment);
}

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
    public void onCreate(String content) {
        postService.createPostComment(content);
    }

    @Override
    public void onEdit(PostComment postComment) {
        postService.updatePostComment(postComment.getId(),"Da sua");
    }

    @Override
    public void onDelete(PostComment postComment) {
        postService.deletePostComment(postComment.getId());
    }

    @Override
    public void onGetSuccess(ArrayList<PostComment> postComments) {
        onResponse(postComments);
    }

    @Override
    public void onSuccess() {
        view.onSuccess();
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }
}
