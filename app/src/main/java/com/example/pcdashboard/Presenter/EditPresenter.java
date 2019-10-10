package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Services.PostService;
import com.example.pcdashboard.View.IEditView;

interface IEditPresenter {
    void onInit();

    void onEdit(String content, String image);

    void onResponse();
}

public class EditPresenter implements IEditPresenter, PostService.EditListener {
    private Context context;
    private IEditView view;
    private PostService postService;

    public EditPresenter(Context context) {
        this.context = context;
        postService = PostService.getInstance(context);
    }

    public void setEditView(IEditView iEditView) {
        this.view = iEditView;
    }

    public void addEditListener() {
        postService.setEditListener(this);
    }

    public void removeEditListener() {
        postService.setEditListener(null);
    }

    @Override
    public void onInit() {
        User self = SharedPreferencesUtil.loadSelf(context);
        view.onInit(self);
    }

    @Override
    public void onEdit(String content, String image) {
        postService.updateClassPost(SharedPreferencesUtil.loadPost(context), content, image);
    }

    @Override
    public void onResponse() {
        view.onSuccess();
    }

    @Override
    public void onSuccess() {
        onResponse();
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }
}
