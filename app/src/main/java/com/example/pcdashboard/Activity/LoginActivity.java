package com.example.pcdashboard.Activity;

import android.os.Bundle;

import com.example.pcdashboard.Fragment.ForgotFragment;
import com.example.pcdashboard.Fragment.LoginFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class LoginActivity extends AppCompatActivity implements IScreenManager {
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
        screenManager.setScreenListener(this);
        screenManager.openLoginScreen(1);
    }
    @Override
    public void openLoginScreen(int idScreen) {
        switch (idScreen){
            case LOGIN_ID:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_login,new LoginFragment()).commit();
                break;
            case FORGOT_ID:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_login,new ForgotFragment()).commit();
                break;
        }
    }

    @Override
    public void openDashboardScreen(int idScreen) {
        //NULL
    }
}
