package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.User;

import java.util.ArrayList;

public interface IUserView {
    void onSuccess(ArrayList<User> users);
    void onFailure();
}
