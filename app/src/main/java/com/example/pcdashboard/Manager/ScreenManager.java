package com.example.pcdashboard.Manager;

import androidx.fragment.app.Fragment;

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

    public void setScreenListener(IScreenManager iScreenManager) {
        this.iScreenManager = iScreenManager;
    }

    public void openLoginScreen(int screenId) {
        iScreenManager.openLoginScreen(screenId);
    }

    public Fragment openDashboardScreen(int screenId) {
        return iScreenManager.openDashboardScreen(screenId);
    }
}
