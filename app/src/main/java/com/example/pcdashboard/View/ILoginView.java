package com.example.pcdashboard.View;

public interface ILoginView {
    void showLoadingDialog();
    void onCheckFailure();
    void onLoginSuccess();
    void onLoginFailure();
}
