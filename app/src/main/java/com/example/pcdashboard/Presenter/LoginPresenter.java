package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.pcdashboard.API.AccountService;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Utility.SharedPreferencesUtil;
import com.example.pcdashboard.View.ILoginView;

public class LoginPresenter implements ILoginPresenter, AccountService.LoginListener {
    private Context context;
    private ILoginView view;
    private AccountService accountService;

    public LoginPresenter(Context context) {
        this.context=context;
        accountService = AccountService.getInstance(context);
    }

    public void setLoginView(ILoginView iLoginView) {
        this.view = iLoginView;
    }

    public void addLoginListener(){
        accountService.setLoginListener(this);
    }

    public void removeLoginListener(){
        accountService.setLoginListener(null);
    }

    @Override
    public void onCheck(String userId, String password) {
        if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(password)){
            view.showLoadingDialog();
            onRequest(userId,password);
        }else {
            view.onCheckFailure();
        }
    }

    @Override
    public void onRequest(String userId, String password) {
        accountService.getToken(userId, password);
    }

    @Override
    public void onResponse() {
        view.onLoginSuccess();
    }

    @Override
    public void changeStatus(boolean status) {
        SharedPreferencesUtil.saveStatus(context,!status);
    }


    @Override
    public void onTokenSuccess(Token token) {
        SharedPreferencesUtil.saveToken(context, token);
        String userId = token.getUserId();
        accountService.getSelf(userId);
    }

    @Override
    public void onSelfSuccess(User self) {
        SharedPreferencesUtil.saveSelf(context, self);
        onResponse();
    }

    @Override
    public void onLoginFailure() {
        view.onLoginFailure();
    }

}
