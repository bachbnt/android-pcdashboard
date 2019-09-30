package com.example.pcdashboard.View;

public interface IForgotView {
    void onInput();
    void showLoadingDialog();
    void onGetSuccess(String email);
    void onCheckFailure();
    void onGetFailure();
}
