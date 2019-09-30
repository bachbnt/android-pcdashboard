package com.example.pcdashboard.View;

public interface ILoginView {
    void onInput();
    void showLoadingDialog();
    void onUpdateScreen();
    void onCheckFailure();
    void onLoginFailure();
}
