package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.pcdashboard.WebServices.AccountService;
import com.example.pcdashboard.View.IForgotView;
interface IForgotPresenter {
    void onCheck(String userId);
    void onRequest(String userId);
    void onResponse();
}
public class ForgotPresenter implements IForgotPresenter, AccountService.ForgotListener {
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

    public void addForgotListener(){
        accountService.setForgotListener(this);
    }
    public void removeForgotListener(){
        accountService.setForgotListener(null);
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
    public void onSuccess() {
        onResponse();
    }

    @Override
    public void onFailure() {
        view.onGetFailure();
    }


}
