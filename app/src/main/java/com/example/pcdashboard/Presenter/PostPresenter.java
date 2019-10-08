package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.View.IPostView;
import com.example.pcdashboard.Services.PostService;

interface IPostPresenter{
    void onPost(String content,String image);
    void onResponse();
}
public class PostPresenter implements IPostPresenter,PostService.PostListener {
    private Context context;
    private IPostView view;
    private PostService postService;

    public PostPresenter(Context context) {
        this.context = context;
        postService=PostService.getInstance(context);
    }
    public void setPostView(IPostView iPostView){
        this.view=iPostView;
    }

    public void addPostListener(){
        postService.setPostListener(this);
    }
    public void removePostListener(){
        postService.setPostListener(null);
    }

    @Override
    public void onPost(String content,String image) {
        postService.createClassPost(content,image);
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
