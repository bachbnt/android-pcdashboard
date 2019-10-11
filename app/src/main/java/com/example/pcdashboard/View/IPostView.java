package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.User;

public interface IPostView {
    void onInit(User self);
    void onCheckFailure();
    void onSuccess();
    void onFailure();
}
