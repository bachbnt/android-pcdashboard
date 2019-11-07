package com.example.pcdashboard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pcdashboard.Dialog.EmailDialog;
import com.example.pcdashboard.Dialog.LoadingDialog;
import com.example.pcdashboard.Fragment.ForgotFragment;
import com.example.pcdashboard.Fragment.LoginFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.R;

public class LoginActivity extends AppCompatActivity implements IScreenManager {
    private DialogFragment dialogFragment;
    private ScreenManager screenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        initialize();
    }

    private void initialize() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        screenManager = ScreenManager.getInstance();
        screenManager.setIScreenManager(this);
        screenManager.openLoginScreen(LOGIN_FRAGMENT);
    }

    @Override
    public void openLoginScreen(String screenName) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        switch (screenName) {
            case DASHBOARD_ACTIVITY:
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
                break;
            case LOGIN_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_login, new LoginFragment()).commit();
                break;
            case FORGOT_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_login, new ForgotFragment()).commit();
                break;
        }
    }

    @Override
    public Fragment openDashboardScreen(String screenName) {
        switch (screenName) {
            case DASHBOARD_ACTIVITY:
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return null;
    }

    @Override
    public void openFeatureScreen(String screenName) {
        //NULL
    }

    @Override
    public void openDialog(String dialogName, User user) {
        switch (dialogName) {
            case LOADING_DIALOG:
                LoadingDialog loadingDialog = new LoadingDialog();
                this.dialogFragment = loadingDialog;
                loadingDialog.show(getSupportFragmentManager(), "loading dialog");
                break;
            case EMAIL_DIALOG:
                EmailDialog emailDialog = new EmailDialog();
                this.dialogFragment = emailDialog;
                emailDialog.show(getSupportFragmentManager(), "email dialog");
                break;
        }
    }

    @Override
    public void closeDialog(String dialogName) {
        switch (dialogName) {
            case LOADING_DIALOG:
                dialogFragment.dismiss();
                break;
            case EMAIL_DIALOG:
                dialogFragment.dismiss();
                break;
        }
    }

}