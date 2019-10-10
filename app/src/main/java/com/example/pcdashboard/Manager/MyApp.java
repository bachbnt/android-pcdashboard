package com.example.pcdashboard.Manager;

import android.Manifest;
import android.app.Application;

import pub.devrel.easypermissions.EasyPermissions;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Notifications.createChannel(this);
    }
}
