package com.example.pcdashboard.Presenter;

public interface IInfoPresenter {
    void onLoad();
    void onCheck(String email,String phone);
    void onRequest(String email,String phone);
    void onResponse();
}
