package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.pcdashboard.API.AccountService;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Utility.SharedPreferences;
import com.example.pcdashboard.View.ILoginView;

public class LoginPresenter implements ILoginPresenter, AccountService.AccountListener {
    private Context context;
    private ILoginView view;
    private AccountService accountService;

    public LoginPresenter(Context context) {
        this.context=context;
        accountService = AccountService.getInstance(context);
        accountService.setListener(this);
    }

    public void setLoginView(ILoginView iLoginView) {
        this.view = iLoginView;
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
        view.onUpdateScreen();
    }


    @Override
    public void onTokenSuccess() {
        Token token = SharedPreferences.loadToken(context);
        String userId = token.getUserId();
        accountService.getSelf(userId);
    }

    @Override
    public void onSelfSuccess() {
        onResponse();
    }

    @Override
    public void onLoginFailure() {
        view.onLoginFailure();
    }
}
