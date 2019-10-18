package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Services.PostService;
import com.example.pcdashboard.View.IEditCommentView;

interface IEditCommentPresenter {
    void onInit();
    void onCheck(String content);
    void onEdit(String content);
}
public class EditCommentPresenter implements PostService.EditCommentListener,IEditCommentPresenter {
    private Context context;
    private IEditCommentView view;
    private PostService postService;

    public EditCommentPresenter(Context context) {
        this.context = context;
        postService = PostService.getInstance(context);
    }

    public void setEditView(IEditCommentView iEditView) {
        this.view = iEditView;
    }

    public void addEditListener() {
        postService.setEditCommentListener(this);
    }

    public void removeEditListener() {
        postService.setEditCommentListener(null);
    }


    @Override
    public void onInit() {
        PostComment postComment=SharedPreferencesUtils.loadPostComment(context);
        view.onInit(postComment);
    }

    @Override
    public void onCheck(String content) {
        if(!TextUtils.isEmpty(content))
            onEdit(content);
        else {view.onCheckFailure();
            Log.i("tag","onCheckFailure cmt");}
    }

    @Override
    public void onEdit(String content) {
        postService.updatePostComment(SharedPreferencesUtils.loadPostComment(context).getId(), content);

    }
    @Override
    public void onSuccess() {
        view.onSuccess();
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }

}
