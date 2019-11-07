package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.pcdashboard.Manager.DatabaseHelper;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Services.PostService;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.View.IClassView;

import java.util.ArrayList;

interface IClassPresenter {
    void onInit();

    void onRequestDatabase();

    void onRequestServer(int number);

    void onResponse(ArrayList<ClassPost> classPosts);

    void onDelete(ClassPost classPost);
}

public class ClassPresenter implements IClassPresenter, PostService.ClassListener {
    class ClassTask extends AsyncTask<String, Void, ArrayList<ClassPost>> {

        @Override
        protected ArrayList<ClassPost> doInBackground(String... strings) {
            ArrayList<ClassPost> classPosts = null;
            if (SharedPreferencesUtils.loadClassId(context).equals("3Y"))
                databaseHelper.loadYearClassPosts(3);
            else if (SharedPreferencesUtils.loadClassId(context).equals("4Y"))
                databaseHelper.loadYearClassPosts(4);
            else databaseHelper.loadClassPosts();
            return classPosts;
        }

        @Override
        protected void onPostExecute(ArrayList<ClassPost> classPosts) {
            super.onPostExecute(classPosts);
            if (classPosts != null) {
                onResponse(classPosts);
            }
            onRequestServer(10);
        }
    }

    private Context context;
    private IClassView view;
    private PostService postService;
    private DatabaseHelper databaseHelper;

    public ClassPresenter(Context context) {
        this.context = context;
        postService = PostService.getInstance(context);
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void setClassView(IClassView iClassView) {
        this.view = iClassView;
    }

    public void addClassListener() {
        postService.setClassListener(this);
    }

    public void removeClassListener() {
        postService.setClassListener(null);
    }

    @Override
    public void onInit() {
        User self = SharedPreferencesUtils.loadSelf(context);
        view.onInit(self);
    }

    @Override
    public void onRequestDatabase() {
        ClassTask classTask = new ClassTask();
        classTask.execute();
    }


    @Override
    public void onRequestServer(int number) {
        postService.getClassPosts(number);
    }

    @Override
    public void onResponse(ArrayList<ClassPost> classPosts) {
        if (view != null)
            view.onSuccess(classPosts);
    }

    @Override
    public void onDelete(ClassPost classPost) {
        postService.deleteClassPost(classPost.getId());
    }

    @Override
    public void onSuccess(ArrayList<ClassPost> classPosts) {
        onResponse(classPosts);
    }

    @Override
    public void onDeleteSuccess() {
        view.onDeleteSuccess();
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }

    @Override
    public void onDeleteFailure() {
        view.onDeleteFailure();
    }
}
