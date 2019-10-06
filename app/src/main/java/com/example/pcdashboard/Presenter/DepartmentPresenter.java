package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.API.PostService;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.View.IDeparmentView;

import java.util.ArrayList;

public class DepartmentPresenter implements IDepartmentPresenter, PostService.DepartmentListener {
    private Context context;
    private IDeparmentView view;
    private PostService postService;

    public DepartmentPresenter(Context context) {
        this.context = context;
        postService=PostService.getInstance(context);
    }

    public void setDepartmentView(IDeparmentView iDeparmentView){
        this.view=iDeparmentView;
    }

    public void addDepartmentListener(){
        postService.setDepartmentListener(this);
    }

    public void removeDepartmentListener(){
        postService.setDepartmentListener(null);
    }
    @Override
    public void onRequest() {
        postService.getDepartmentPosts();
    }

    @Override
    public void onResponse(ArrayList<DepartmentPost> departmentPosts) {
        view.onUpdate(departmentPosts);
    }


    @Override
    public void onSuccess(ArrayList<DepartmentPost> departmentPosts) {
        onResponse(departmentPosts);
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }
}
