package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.View.IAccountView;

interface IAccountPresenter {
    void onInit();

    void onLogout();

    void changeStatus(boolean status);

    void changeFirstSchedule();
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
        User self = SharedPreferencesUtils.loadSelf(context);
        view.onInit(self);
    }

    @Override
    public void onLogout() {
        SharedPreferencesUtils.clearSelf(context);
        SharedPreferencesUtils.clearToken(context);
    }

    @Override
    public void changeStatus(boolean status) {
        SharedPreferencesUtils.saveStatusLogin(context, status);
    }

    @Override
    public void changeFirstSchedule() {
        SharedPreferencesUtils.saveFirstRequest(context,true);
    }
}
