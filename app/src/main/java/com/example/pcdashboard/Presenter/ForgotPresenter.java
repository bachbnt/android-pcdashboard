package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.pcdashboard.API.AccountService;
import com.example.pcdashboard.View.IForgotView;

public class ForgotPresenter implements IForgotPresenter,AccountService.AccountListener {
    private Context context;
    private IForgotView view;
    private AccountService accountService;

    public ForgotPresenter(Context context) {
        this.context = context;
        accountService = AccountService.getInstance(context);
        accountService.setListener(this);
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
        accountService.forgetPassword(userId);
    }

    @Override
    public void onResponse() {
        view.onGetSuccess();
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
    public void onForgotSuccess() {
        onResponse();
        Log.i("tag","onForgotSuccess");
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
