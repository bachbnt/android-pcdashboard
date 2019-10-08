package com.example.pcdashboard.Manager;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Model.User;

public interface IScreenManager {
    String LOGIN_ACTIVITY="LOGIN_ACTIVITY";
    String LOGIN_FRAGMENT = "LOGIN_FRAGMENT";
    String FORGOT_FRAGMENT = "FORGOT_FRAGMENT";

    String DASHBOARD_ACTIVITY = "DASHBOARD_ACTIVITY";
    String DASHBOARD_FRAGMENT="DASHBOARD_FRAGMENT";
    String DEPARTMENT_FRAGMENT = "DEPARTMENT_FRAGMENT";
    String CLASS_FRAGMENT = "CLASS_FRAGMENT";
    String CONTACT_FRAGMENT = "CONTACT_FRAGMENT";
    String ACCOUNT_FRAGMENT = "ACCOUNT_FRAGMENT";
    String INFO_DIALOG="INFO_DIALOG";
    String LOADING_DIALOG="LOADING_DIALOG";
    String EMAIL_DIALOG="EMAIL_DIALOG";
    String COMMENT_DIALOG="COMMENT_DIALOG";

    String POST_FRAGMENT="POST_FRAGMENT";
    String INFO_FRAGMENT="INFO_FRAGMENT";
    String PASSWORD_FRAGMENT="PASSWORD_FRAGMENT";

    void openLoginScreen(String screenName);

    Fragment openDashboardScreen(String screenName);

    void openFeatureScreen(String screenName);
    void openDialog(String dialogName, User user);
    void closeDialog(String dialogName);
}
