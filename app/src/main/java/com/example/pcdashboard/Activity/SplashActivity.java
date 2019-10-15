package com.example.pcdashboard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initialize();
    }

    private void initialize() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (SharedPreferencesUtil.loadStatusLogin(getApplicationContext())) {
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1000);
    }
}
