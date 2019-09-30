package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.API.PostService;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.View.IDeparmentView;

import java.util.ArrayList;

public class DepartmentPresenter implements IDepartmentPresenter,PostService.PostListener {
    private Context context;
    private IDeparmentView view;
    private PostService postService;

    public DepartmentPresenter(Context context) {
        this.context = context;
        postService=PostService.getInstance(context);
        postService.setListener(this);
    }

    public void setDepartmentView(IDeparmentView iDeparmentView){
        this.view=iDeparmentView;
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
    public void onDepartmentSuccess(ArrayList<DepartmentPost> departmentPosts) {
        onResponse(departmentPosts);
    }

    @Override
    public void onClassSuccess(ArrayList<ClassPost> classPosts) {
        //NULL
    }


    @Override
    public void onFailure() {
        view.onFailure();
    }
}
