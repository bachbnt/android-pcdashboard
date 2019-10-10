package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.User;

public interface IEditView {
    void onInit(User self);
    void onSuccess();
    void onFailure();
}
