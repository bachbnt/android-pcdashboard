package com.example.pcdashboard.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.pcdashboard.Dialog.CommentDialog;
import com.example.pcdashboard.Dialog.InfoDialog;
import com.example.pcdashboard.Fragment.AccountFragment;
import com.example.pcdashboard.Fragment.ClassFragment;
import com.example.pcdashboard.Fragment.ContactFragment;
import com.example.pcdashboard.Fragment.DashboardFragment;
import com.example.pcdashboard.Fragment.DepartmentFragment;
import com.example.pcdashboard.Fragment.InfoFragment;
import com.example.pcdashboard.Fragment.PasswordFragment;
import com.example.pcdashboard.Fragment.PostFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.R;

import pub.devrel.easypermissions.EasyPermissions;

public class DashboardActivity extends AppCompatActivity implements IScreenManager {
    private ScreenManager screenManager;
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
    }


    private void initialize() {
        screenManager = ScreenManager.getInstance();
        screenManager.setIScreenManager(this);
        screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
        EasyPermissions.requestPermissions(this, "Access for storage", 101, galleryPermissions);
    }

    @Override
    public void openLoginScreen(String screenName) {
        switch (screenName){
            case LOGIN_ACTIVITY:
                Intent intent=new Intent(DashboardActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
        }
    }

    @Override
    public Fragment openDashboardScreen(String screenName) {
        Fragment fragment = null;
        switch (screenName) {
            case DEPARTMENT_FRAGMENT:
                fragment = new DepartmentFragment();
                break;
            case CLASS_FRAGMENT:
                fragment = new ClassFragment();
                break;
            case CONTACT_FRAGMENT:
                fragment = new ContactFragment();
                break;
            case ACCOUNT_FRAGMENT:
                fragment = new AccountFragment();
                break;
        }
        return fragment;
    }

    @Override
    public void openFeatureScreen(String screenName) {
        switch (screenName) {
            case DASHBOARD_FRAGMENT:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_dashboard, new DashboardFragment()).commit();
                break;
            case POST_FRAGMENT:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_dashboard, new PostFragment()).commit();
                break;
            case INFO_FRAGMENT:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_dashboard, new InfoFragment()).commit();
                break;
            case PASSWORD_FRAGMENT:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_dashboard, new PasswordFragment()).commit();
                break;
        }
    }

    @Override
    public void openDialog(String dialogName, User user) {
        switch (dialogName) {
            case INFO_DIALOG:
                InfoDialog dialog = new InfoDialog(user);
                dialog.show(getSupportFragmentManager(), "info dialog");
                break;
            case COMMENT_DIALOG:
                Log.i("tag", "openDialog");
                CommentDialog commentDialog = new CommentDialog();
                commentDialog.show(getSupportFragmentManager(), "comment dialog");
                break;
        }
    }

    @Override
    public void closeDialog(String dialogName) {
        //NULL
    }

}