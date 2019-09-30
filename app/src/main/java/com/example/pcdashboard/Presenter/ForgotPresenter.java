package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.pcdashboard.API.AccountService;
import com.example.pcdashboard.View.IForgotView;

public class ForgotPresenter implements IForgotPresenter,AccountService.AccountListener {
    private Context context;
    private IForgotView view;
    private AccountService accountService;

    public ForgotPresenter(Context context) {
        this.context = context;
        accountService = AccountService.getInstance(context);
    }
    public void setForgotView(IForgotView iForgotView){
        this.view=iForgotView;
    }

    @Override
    public void onCheck(String userId) {
        if(TextUtils.isEmpty(userId)){
            view.showLoadingDialog();
            onRequest(userId);
        }else view.onCheckFailure();
    }

    @Override
    public void onRequest(String userId) {
        accountService.forgetPassword(userId);
    }

    @Override
    public void onResponse(String email) {
        view.onGetSuccess(email);
    }

    @Override
    public void onTokenSuccess() {
        //NULL
    }

    @Override
    public void onSelfSuccess() {
        //NULL
    }

    @Override
    public void onForgotSuccess(String email) {
        onResponse(email);
    }

    @Override
    public void onLoginFailure() {
        //NULL
    }
}
