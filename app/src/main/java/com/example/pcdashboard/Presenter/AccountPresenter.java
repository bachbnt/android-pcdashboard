package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Utility.SharedPreferences;
import com.example.pcdashboard.View.IAccountView;

public class AccountPresenter implements IAccountPresenter {
    private Context context;
    private IAccountView view;

    public AccountPresenter(Context context) {
        this.context=context;
    }

    public void setAccountView(IAccountView iAccountView){
        this.view=iAccountView;
    }

    @Override
    public void loadSelf() {
        User self= SharedPreferences.loadSelf(context);
        view.onUpdate(self);
    }
}
