package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Services.PostService;
import com.example.pcdashboard.View.IPostView;

interface IPostPresenter {
    void onInit();

    void onCheck(String content, String image);

    void onPost(String content, String image);

    void onResponse();
}

public class PostPresenter implements IPostPresenter, PostService.PostListener {
    private Context context;
    private IPostView view;
    private PostService postService;

    public PostPresenter(Context context) {
        this.context = context;
        postService = PostService.getInstance(context);
    }

    public void setPostView(IPostView iPostView) {
        this.view = iPostView;
    }

    public void addPostListener() {
        postService.setPostListener(this);
    }

    public void removePostListener() {
        postService.setPostListener(null);
    }

    @Override
    public void onInit() {
        User self = SharedPreferencesUtil.loadSelf(context);
        view.onInit(self);
    }

    @Override
    public void onCheck(String content, String image) {
        if (TextUtils.isEmpty(content) && image == null)
            view.onCheckFailure();
        else onPost(content, image);
    }

    @Override
    public void onPost(String content, String image) {
        postService.createClassPost(content, image);
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
