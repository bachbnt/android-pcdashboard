package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Services.PostService;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.View.IClassView;

import java.util.ArrayList;

interface IClassPresenter {
    void onRequest();
    void onResponse(ArrayList<ClassPost> classPosts);
    void onEdit(ClassPost classPost);
    void onDelete(ClassPost classPost);
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
    public void onEdit(ClassPost classPost) {
//        postService.updateClassPost(classPost.getId(),classPost.getContent(),classPost.getImage());
    }

    @Override
    public void onDelete(ClassPost classPost) {
        postService.deleteClassPost(classPost.getId());
    }

    @Override
    public void onGetSuccess(ArrayList<ClassPost> classPosts) {
        onResponse(classPosts);
    }

    @Override
    public void onEditSuccess() {
        view.onSuccess();
    }

    @Override
    public void onDeleteSuccess() {
        view.onSuccess();
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }
}
