package com.example.pcdashboard.Presenter;

import com.example.pcdashboard.View.IAccountView;

public class AccountPresenter implements IAccountPresenter {
    private IAccountView iAccountView;

    public AccountPresenter(IAccountView iAccountView) {
        this.iAccountView = iAccountView;
    }

    @Override
    public void onDestroy() {
        iAccountView=null;
    }

    @Override
    public void loadInformation() {

    }
}
