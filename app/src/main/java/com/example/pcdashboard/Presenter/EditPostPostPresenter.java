package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Services.PostService;
import com.example.pcdashboard.View.IEditPostView;

interface IEditPostPresenter {
    void onInit();
    void onCheck(String content);
    void onEdit(String content);
}

public class EditPostPostPresenter implements IEditPostPresenter, PostService.EditPostListener {
    private Context context;
    private IEditPostView view;
    private PostService postService;

    public EditPostPostPresenter(Context context) {
        this.context = context;
        postService = PostService.getInstance(context);
    }

    public void setEditView(IEditPostView iEditView) {
        this.view = iEditView;
    }

    public void addEditListener() {
        postService.setEditPostListener(this);
    }

    public void removeEditListener() {
        postService.setEditPostListener(null);
    }

    @Override
    public void onInit() {
        User self = SharedPreferencesUtils.loadSelf(context);
        ClassPost classPost=SharedPreferencesUtils.loadClassPost(context);
        view.onInit(self,classPost);
    }

    @Override
    public void onCheck(String content) {
        if(!TextUtils.isEmpty(content))
            onEdit(content);
        else {view.onCheckFailure();
            Log.i("tag","onCheckFailure edit");}
    }

    @Override
    public void onEdit(String content) {
        postService.updateClassPost(SharedPreferencesUtils.loadClassPost(context).getId(), content);
    }

    @Override
    public void onSuccess() {
        if(view!=null)
        view.onSuccess();
    }

    @Override
    public void onFailure() {
        if(view!=null)
        view.onFailure();
    }
}
