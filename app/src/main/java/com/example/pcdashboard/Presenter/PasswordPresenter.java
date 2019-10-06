package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.WebServices.AccountService;
import com.example.pcdashboard.View.IPasswordView;

interface IPasswordPresenter {
}
public class PasswordPresenter implements IPasswordPresenter,AccountService.PasswordListener {
    private Context context;
    private IPasswordView view;
    private AccountService accountService;

    public PasswordPresenter(Context context) {
        this.context = context;
        accountService=AccountService.getInstance(context);
    }
    public void setPasswordView(IPasswordView iPasswordView){
        this.view=iPasswordView;
    }

    public void addPasswordListener(){
        accountService.setPasswordListener(this);
    }
    public void removePasswordListener(){
        accountService.setPasswordListener(null);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }
}
