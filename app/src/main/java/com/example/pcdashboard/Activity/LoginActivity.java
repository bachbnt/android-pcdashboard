package com.example.pcdashboard.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pcdashboard.Fragment.ForgotFragment;
import com.example.pcdashboard.Fragment.LoginFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class LoginActivity extends AppCompatActivity implements IScreenManager {
    private final int DASHBOARD_ID=0;
    private final int LOGIN_ID=1;
    private final int FORGOT_ID=2;
    private ScreenManager screenManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    private void initialize(){
        screenManager=ScreenManager.getInstance();
        screenManager.setScreenManager(this);
        screenManager.openLoginScreen(1);
    }
    @Override
    public void openLoginScreen(int screenId) {
        switch (screenId){
            case DASHBOARD_ID:
                Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
                break;
            case LOGIN_ID:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_login,new LoginFragment()).commit();
                break;
            case FORGOT_ID:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_login,new ForgotFragment()).commit();
                break;
        }
    }

    @Override
    public Fragment openDashboardScreen(int screenId) {
        //NULL
        return null;
    }
}