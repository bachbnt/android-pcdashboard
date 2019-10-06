package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.User;

public interface IInfoView {
    void onLoad(User self);
    void onCheckFailure();
    void onUpdateSuccess();
    void onUpdateFailure();
}
