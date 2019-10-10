package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.View.IAccountView;

interface IAccountPresenter {
    void onInit();

    void onLogout();

    void changeStatus(boolean status);
}

public class AccountPresenter implements IAccountPresenter {
    private Context context;
    private IAccountView view;


    public AccountPresenter(Context context) {
        this.context = context;
    }

    public void setAccountView(IAccountView iAccountView) {
        this.view = iAccountView;
    }

    @Override
    public void onInit() {
        User self = SharedPreferencesUtil.loadSelf(context);
        view.onInit(self);
    }

    @Override
    public void onLogout() {
        SharedPreferencesUtil.clearSelf(context);
        SharedPreferencesUtil.clearToken(context);
    }

    @Override
    public void changeStatus(boolean status) {
        SharedPreferencesUtil.saveStatus(context, !status);
    }
}
