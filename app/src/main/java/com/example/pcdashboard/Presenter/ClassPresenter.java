package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.WebServices.PostService;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.View.IClassView;

import java.util.ArrayList;

interface IClassPresenter {
    void onRequest();
    void onResponse(ArrayList<ClassPost> classPosts);
}
public class ClassPresenter implements IClassPresenter, PostService.ClassListener {
    private Context context;
    private IClassView view;
    private PostService postService;

    public ClassPresenter(Context context) {
        this.context = context;
        postService=PostService.getInstance(context);
    }
    public void setClassView(IClassView iClassView){
        this.view=iClassView;
    }

    public void addClassListener(){
        postService.setClassListener(this);
    }

    public void removeClassListener(){
        postService.setClassListener(null);
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
