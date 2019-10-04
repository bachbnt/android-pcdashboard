package com.example.pcdashboard.Presenter;

import com.example.pcdashboard.Model.User;

public interface ILoginPresenter {
    void onCheck(String userId,String password);
    void onRequest(String userId,String password);
    void onResponse();
    void changeStatus(boolean status);
}
