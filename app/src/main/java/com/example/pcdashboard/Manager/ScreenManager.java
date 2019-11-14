package com.example.pcdashboard.Manager;

import android.app.ActivityManager;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Model.User;

import java.util.List;

public class ScreenManager {
    private static ScreenManager screenManager;
    private IScreenManager iScreenManager;

    private ScreenManager() {
    }

    public static ScreenManager getInstance() {
        if (screenManager == null)
            screenManager = new ScreenManager();
        return screenManager;
    }

    public void setIScreenManager(IScreenManager iScreenManager) {
        this.iScreenManager = iScreenManager;
    }

    public void openLoginScreen(String screenName) {
        iScreenManager.openLoginScreen(screenName);
    }

    public Fragment openDashboardScreen(String screenName) {
        return iScreenManager.openDashboardScreen(screenName);
    }

    public void openFeatureScreen(String screenName){
        iScreenManager.openFeatureScreen(screenName);
    }

    public void openDialog(String dialogName, User user){
        iScreenManager.openDialog(dialogName,user);
    }
    public void closeDialog(String dialogName){
        iScreenManager.closeDialog(dialogName);
    }
}
