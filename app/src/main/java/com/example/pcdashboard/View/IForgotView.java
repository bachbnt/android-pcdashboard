package com.example.pcdashboard.View;

public interface IForgotView {
    void showLoadingDialog();
    void onGetSuccess();
    void onCheckFailure();
    void onGetFailure();
}
