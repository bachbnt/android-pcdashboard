package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.User;

public interface IUpdateInfoView {
    void onLoad(User self);
    void onCheckFailure();
    void onUpdateSuccess();
    void onUpdateFailure();
}
