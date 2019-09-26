package com.example.pcdashboard.Presenter;

import com.example.pcdashboard.Model.User;

public interface ILoginPresenter {
    void onRequest(String userId,String password);
    void onResponse();
}
