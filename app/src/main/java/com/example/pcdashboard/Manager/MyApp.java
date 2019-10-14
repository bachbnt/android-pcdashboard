package com.example.pcdashboard.Manager;

import android.app.Application;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        NotificationsUtil.createChannel(this);
    }
}
