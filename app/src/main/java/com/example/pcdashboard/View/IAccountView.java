package com.example.pcdashboard.View;

import com.example.pcdashboard.Presenter.IAccountPresenter;

public interface IAccountView {
    void setPresenter(IAccountPresenter iAccountPresenter);
    void onClickRow();
}
