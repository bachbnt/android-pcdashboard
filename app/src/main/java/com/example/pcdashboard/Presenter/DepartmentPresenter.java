package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.pcdashboard.Manager.DatabaseHelper;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Services.PostService;
import com.example.pcdashboard.View.IDeparmentView;

import java.util.ArrayList;

interface IDepartmentPresenter {
    void onRequestDatabase();

    void onRequestServer(int number);

    void onResponse(ArrayList<DepartmentPost> departmentPosts);
}

public class DepartmentPresenter implements IDepartmentPresenter, PostService.DepartmentListener {
    class DepartmentTask extends AsyncTask<String, Void, ArrayList<DepartmentPost>> {

        @Override
        protected ArrayList<DepartmentPost> doInBackground(String... strings) {
            ArrayList<DepartmentPost> departmentPosts = databaseHelper.loadDepartmentPosts();
            return departmentPosts;
        }

        @Override
        protected void onPostExecute(ArrayList<DepartmentPost> departmentPosts) {
            super.onPostExecute(departmentPosts);
            if (departmentPosts != null) {
                onResponse(departmentPosts);
                Log.i("tag", "departmentPosts "+departmentPosts.size());
            }
            onRequestServer(10);
        }
    }

    private Context context;
    private IDeparmentView view;
    private PostService postService;
    private DatabaseHelper databaseHelper;

    public DepartmentPresenter(Context context) {
        this.context = context;
        postService = PostService.getInstance(context);
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void setDepartmentView(IDeparmentView iDeparmentView) {
        this.view = iDeparmentView;
    }

    public void addDepartmentListener() {
        postService.setDepartmentListener(this);
    }

    public void removeDepartmentListener() {
        postService.setDepartmentListener(null);
    }

    @Override
    public void onRequestDatabase() {
        DepartmentTask databaseTask = new DepartmentTask();
        databaseTask.execute();
    }


    @Override
    public void onRequestServer(int number) {
        postService.getDepartmentPosts(number);
    }

    @Override
    public void onResponse(ArrayList<DepartmentPost> departmentPosts) {
        if(view!=null)
            view.onSuccess(departmentPosts);
    }

    @Override
    public void onSuccess(ArrayList<DepartmentPost> departmentPosts) {
        onResponse(departmentPosts);
    }

    @Override
    public void onFailure() {
        if(view!=null)
        view.onFailure();
    }
}
