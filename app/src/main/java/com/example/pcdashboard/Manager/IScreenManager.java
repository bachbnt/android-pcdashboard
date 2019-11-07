package com.example.pcdashboard.Manager;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Model.User;

public interface IScreenManager {
    int TAB_DEPARTMENT=0;
    int TAB_CLASS=1;
    int TAB_CONTACT=2;
    int TAB_ACCOUNT=3;

    String LOGIN_ACTIVITY="LOGIN_ACTIVITY";
    String LOGIN_FRAGMENT = "LOGIN_FRAGMENT";
    String FORGOT_FRAGMENT = "FORGOT_FRAGMENT";

    String DASHBOARD_ACTIVITY = "DASHBOARD_ACTIVITY";
    String DASHBOARD_FRAGMENT="DASHBOARD_FRAGMENT";
    String DEPARTMENT_FRAGMENT = "DEPARTMENT_FRAGMENT";
    String CLASS_FRAGMENT = "CLASS_FRAGMENT";
    String SELECT_CLASS_FRAGMENT="SELECT_CLASS_FRAGMENT";
    String CONTACT_FRAGMENT = "CONTACT_FRAGMENT";
    String ACCOUNT_FRAGMENT = "ACCOUNT_FRAGMENT";
    String CLASS_FRAGMENT_TEACHER="CLASS_FRAGMENT_TEACHER";

    String INFO_DIALOG="INFO_DIALOG";
    String LOADING_DIALOG="LOADING_DIALOG";
    String EMAIL_DIALOG="EMAIL_DIALOG";
    String COMMENT_DIALOG="COMMENT_DIALOG";
    String EDIT_DIALOG="EDIT_DIALOG";

    String WEB_FRAGMENT="WEB_FRAGMENT";
    String POST_FRAGMENT="POST_FRAGMENT";
    String EDIT_FRAGMENT="EDIT_FRAGMENT";
    String INFO_FRAGMENT="INFO_FRAGMENT";
    String PASSWORD_FRAGMENT="PASSWORD_FRAGMENT";
    String SCHEDULE_FRAGMENT="SCHEDULE_FRAGMENT";
    String EXAM_FRAGMENT="EXAM_FRAGMENT";
    String GUIDE_FRAGMENT="GUIDE_FRAGMENT";
    String FEEDBACK_FRAGMENT="FEEDBACK_FRAGMENT";
    String DEVELOPER_FRAGMENT="DEVELOPER_FRAGMENT";
    String CHAT_FRAGMENT="CHAT_FRAGMENT";
    String USER_FRAGMENT="USER_FRAGMENT";

    void openLoginScreen(String screenName);

    Fragment openDashboardScreen(String screenName);

    void openFeatureScreen(String screenName);
    void openDialog(String dialogName, User user);
    void closeDialog(String dialogName);
}
