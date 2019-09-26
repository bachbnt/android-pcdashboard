package com.example.pcdashboard.Manager;

import androidx.fragment.app.Fragment;

public interface IScreenManager {
    String DASHBOARD_ACTIVITY = "DASHBOARD_ACTIVITY";
    String LOGIN_FRAGMENT = "LOGIN_FRAGMENT";
    String FORGOT_FRAGMENT = "FORGOT_FRAGMENT";
    String DEPARTMENT_FRAGMENT = "DEPARTMENT_FRAGMENT";
    String CLASSROOM_FRAGMENT = "CLASSROOM_FRAGMENT";
    String CHAT_FRAGMENT = "CHAT_FRAGMENT";
    String ACCOUNT_FRAGMENT = "ACCOUNT_FRAGMENT";

    void openLoginScreen(String screenName);

    Fragment openDashboardScreen(String screenName);
}
