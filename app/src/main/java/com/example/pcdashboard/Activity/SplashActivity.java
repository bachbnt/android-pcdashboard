package com.example.pcdashboard.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pcdashboard.R;
import com.example.pcdashboard.Manager.SharedPreferencesUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent;
        if (SharedPreferencesUtil.loadStatus(this))
            intent = new Intent(SplashActivity.this, DashboardActivity.class);
        else
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
