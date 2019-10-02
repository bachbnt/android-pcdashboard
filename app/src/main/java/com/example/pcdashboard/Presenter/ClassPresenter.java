package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.API.PostService;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.View.IClassView;

import java.util.ArrayList;

public class ClassPresenter implements IClassPresenter, PostService.ClassListener {
    private Context context;
    private IClassView view;
    private PostService postService;

    public ClassPresenter(Context context) {
        this.context = context;
        postService=PostService.getInstance(context);
        postService.setClassListener(this);
    }
    public void setClassView(IClassView iClassView){
        this.view=iClassView;
    }


    @Override
    public void onRequest() {
        postService.getClassPosts();
    }

    @Override
    public void onResponse(ArrayList<ClassPost> classPosts) {
        view.onUpdate(classPosts);
    }

    @Override
    public void onSuccess(ArrayList<ClassPost> classPosts) {
        onResponse(classPosts);
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }
}
