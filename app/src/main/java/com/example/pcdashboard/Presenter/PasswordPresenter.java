package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.pcdashboard.WebServices.AccountService;
import com.example.pcdashboard.View.IPasswordView;

interface IPasswordPresenter {
    void onCheck(String oldPassword,String newPassword,String retypePassword);
    void onRequest(String oldPassword,String newPassword);
    void onResponse();
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
    public void onCheck(String oldPassword, String newPassword,String retypePassword) {
        if(!TextUtils.isEmpty(oldPassword)&&!TextUtils.isEmpty(newPassword)&&retypePassword.equals(newPassword)){
            onRequest(oldPassword,newPassword);
        }else view.onCheckFailure();
    }

    @Override
    public void onRequest(String oldPassword, String newPassword) {
        accountService.changePassword(oldPassword,newPassword);
    }

    @Override
    public void onResponse() {
        view.onChangeSuccess();
    }

    @Override
    public void onSuccess() {
        onResponse();
    }

    @Override
    public void onFailure() {
        view.onChangeFailure();
    }
}
