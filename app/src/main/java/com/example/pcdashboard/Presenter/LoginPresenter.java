package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.API.AccountService;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Utility.SharedPreferences;
import com.example.pcdashboard.View.ILoginView;

public class LoginPresenter implements ILoginPresenter, AccountService.AccountListener {
    private ILoginView view;
    private AccountService accountService;
    private Context context;

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
        if(userId!=null&&password!=null){
            onRequest(userId,password);
        }else {
            view.onCheckFail();
        }
    }

    @Override
    public void onRequest(String userId, String password) {
        accountService.getToken(userId, password);
    }

    @Override
    public void onResponse() {
        view.onUpdate();
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
    public void onLoginFail() {
        view.onLoginFail();
    }
}
