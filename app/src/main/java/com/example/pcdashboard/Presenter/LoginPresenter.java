package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.pcdashboard.Fragment.WebFragment;
import com.example.pcdashboard.Services.AccountService;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Services.WebService;
import com.example.pcdashboard.View.ILoginView;
interface ILoginPresenter {
    void onCheck(String userId,String password);
    void onRequest(String userId,String password);
    void onResponse();
    void changeLoginStatus(boolean status);
}
public class LoginPresenter implements ILoginPresenter, AccountService.LoginListener {
    private Context context;
    private ILoginView view;
    private AccountService accountService;
    private WebService webService;

    public LoginPresenter(Context context) {
        this.context=context;
        accountService = AccountService.getInstance(context);
        webService=WebService.getInstance(context);
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
    public void changeLoginStatus(boolean status) {
        SharedPreferencesUtils.saveStatusLogin(context,status);
    }


    @Override
    public void onTokenSuccess(Token token) {
        SharedPreferencesUtils.saveToken(context, token);
        accountService.getSelf();
    }

    @Override
    public void onSelfSuccess(User self) {
        SharedPreferencesUtils.saveSelf(context, self);
        webService.sendFCMToken(SharedPreferencesUtils.loadFCMToken(context));
        Log.i("tag", "sendFCMToken "+SharedPreferencesUtils.loadFCMToken(context));
        onResponse();
    }

    @Override
    public void onLoginFailure() {
        view.onLoginFailure();
    }

}