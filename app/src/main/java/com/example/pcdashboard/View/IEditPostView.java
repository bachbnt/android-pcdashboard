package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.User;

public interface IEditPostView {
    void onInit(User self, ClassPost classPost);
    void onCheckFailure();
    void onSuccess();
    void onFailure();
}
