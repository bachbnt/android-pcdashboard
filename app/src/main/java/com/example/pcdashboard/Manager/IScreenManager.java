package com.example.pcdashboard.Manager;

import androidx.fragment.app.Fragment;

public interface IScreenManager {
    String DASHBOARD_ACTIVITY= "DASHBOARD_ACTIVITY";
    String LOGIN_FRAGMENT = 1;
    int FORGOT_FRAGMENT= 2;
    int DEPARTMENT_FRAGMENT = 0;
    int CLASSROOM_FRAGMENT = 1;
    int CHAT_FRAGMENT= 2;
    int ACCOUNT_FRAGMENT= 3;
    void openLoginScreen(int screenId);
    Fragment openDashboardScreen(int screenId);
}
