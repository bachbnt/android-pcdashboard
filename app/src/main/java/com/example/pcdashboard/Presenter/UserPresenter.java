package com.example.pcdashboard.Presenter;

import android.content.Context;

import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Services.ContactService;
import com.example.pcdashboard.View.IChatView;
import com.example.pcdashboard.View.IUserView;

import java.util.ArrayList;

interface IUserPresenter {
    void onRequest();

    void onResponse(ArrayList<User> users);
}
public class UserPresenter implements IUserPresenter, ContactService.UserListener {
    private Context context;
    private IUserView view;
    private ContactService contactService;
    public UserPresenter(Context context) {
        this.context = context;
        contactService = ContactService.getInstance(context);
    }
    public void setUserView(IUserView iUserView){
        this.view=iUserView;
    }

    public void addUserListener(){
        contactService.setUserListener(this);
    }
    public void removeUserListener(){
        contactService.setUserListener(null);
    }

    @Override
    public void onRequest() {
        contactService.getAllUsers();
    }

    @Override
    public void onResponse(ArrayList<User> users) {
        view.onSuccess(users);
    }

    @Override
    public void onSuccess(ArrayList<User> users) {
        onResponse(users);
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }
}
