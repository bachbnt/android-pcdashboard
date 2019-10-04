package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.pcdashboard.API.AccountService;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.View.IForgotView;

public class ForgotPresenter implements IForgotPresenter,AccountService.AccountListener {
    private Context context;
    private IForgotView view;
    private AccountService accountService;

    public ForgotPresenter(Context context) {
        this.context = context;
        accountService = AccountService.getInstance(context);
        accountService.setAccountListener(this);
    }
    public void setForgotView(IForgotView iForgotView){
        this.view=iForgotView;
    }

    @Override
    public void onCheck(String userId) {
        if(!TextUtils.isEmpty(userId)){
            view.showLoadingDialog();
            onRequest(userId);
        }else view.onCheckFailure();
    }

    @Override
    public void onRequest(String userId) {
        accountService.forgotPassword(userId);
    }

    @Override
    public void onResponse() {
        view.onGetSuccess();
    }

    @Override
    public void onTokenSuccess(Token token) {
        //NULL
    }

    @Override
    public void onSelfSuccess(User self) {
        //NULL
    }

    @Override
    public void onForgotSuccess() {
        onResponse();
    }

    @Override
    public void onForgotFailure() {
        view.onGetFailure();
    }

    @Override
    public void onLoginFailure() {
        //NULL
    }
}
