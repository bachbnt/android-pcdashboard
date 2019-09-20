package com.example.pcdashboard.Manager;

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
    public void setScreenListener(IScreenManager iScreenManager){
        this.iScreenManager=iScreenManager;
    }
    public void openLoginScreen(int idScreen){
        iScreenManager.openLoginScreen(idScreen);
    }
    public void openDashboardScreen(int idScreen){
        iScreenManager.openDashboardScreen(idScreen);
    }
}
