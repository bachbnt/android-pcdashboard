package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.example.pcdashboard.Manager.DatabaseHelper;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Services.ContactService;
import com.example.pcdashboard.View.IUserView;

import java.util.ArrayList;

interface IUserPresenter {
    void onRequestDatabase();

    void onRequestServer();

    void onResponse(ArrayList<User> users);
}

public class UserPresenter implements IUserPresenter, ContactService.UserListener {
    class UserTask extends AsyncTask<String, Void, ArrayList<User>> {

        @Override
        protected ArrayList<User> doInBackground(String... strings) {
            ArrayList<User> users;
            if (classId.equals("GV"))
                users = databaseHelper.loadUserTeachers();
            else if(SharedPreferencesUtils.loadClassId(context).equals("3Y"))
                users=databaseHelper.loadYearStudents(3);
            else if(SharedPreferencesUtils.loadClassId(context).equals("4Y"))
                users=databaseHelper.loadYearStudents(4);
            else
                users = databaseHelper.loadUserStudents();
            return users;
        }

        @Override
        protected void onPostExecute(ArrayList<User> users) {
            super.onPostExecute(users);
            if (users != null) {
                onResponse(users);
            }
            onRequestServer();
        }
    }

    private String classId;
    private Context context;
    private IUserView view;
    private ContactService contactService;
    private DatabaseHelper databaseHelper;

    public UserPresenter(Context context, String classId) {
        this.context = context;
        this.classId = classId;
        contactService = ContactService.getInstance(context);
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void setUserView(IUserView iUserView) {
        this.view = iUserView;
    }

    public void addUserListener() {
        contactService.setUserListener(this);
    }

    public void removeUserListener() {
        contactService.setUserListener(null);
    }

    @Override
    public void onRequestDatabase() {
        UserTask userTask = new UserTask();
        userTask.execute();
    }

    @Override
    public void onRequestServer() {
        contactService.getUsers(classId);
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
