package com.example.pcdashboard.Presenter;

import com.example.pcdashboard.View.ILoginView;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }
}
