package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.IAccountPresenter;

public interface IAccountView {
    void onShowSelf(User self);
    void showDialog();
}
