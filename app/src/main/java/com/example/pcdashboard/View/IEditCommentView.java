package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Model.User;

public interface IEditCommentView {
    void onInit(PostComment postComment);
    void onCheckFailure();
    void onSuccess();
    void onFailure();
}
