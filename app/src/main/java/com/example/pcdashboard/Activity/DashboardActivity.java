package com.example.pcdashboard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pcdashboard.Adapter.PagerAdapter;
import com.example.pcdashboard.Dialog.CommentDialog;
import com.example.pcdashboard.Dialog.InfoDialog;
import com.example.pcdashboard.Fragment.AccountFragment;
import com.example.pcdashboard.Fragment.ClassFragment;
import com.example.pcdashboard.Fragment.ContactFragment;
import com.example.pcdashboard.Fragment.DashboardFragment;
import com.example.pcdashboard.Fragment.DepartmentFragment;
import com.example.pcdashboard.Fragment.ForgotFragment;
import com.example.pcdashboard.Fragment.InfoFragment;
import com.example.pcdashboard.Fragment.LoginFragment;
import com.example.pcdashboard.Fragment.PasswordFragment;
import com.example.pcdashboard.Fragment.PostFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity implements IScreenManager {
    private ScreenManager screenManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
    }


    private void initialize() {
        screenManager = ScreenManager.getInstance();
        screenManager.setScreenManager(this);
        screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);

    }

    @Override
    public void openLoginScreen(String screenName) {
        //NULL
    }

    @Override
    public Fragment openDashboardScreen(String screenName) {
        Fragment fragment = null;
        switch (screenName) {
            case DEPARTMENT_FRAGMENT:
                fragment = new DepartmentFragment();
                break;
            case CLASSROOM_FRAGMENT:
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
    public void openDialog(String dialogName) {
        switch (dialogName) {
            case INFO_DIALOG:
                InfoDialog dialog = new InfoDialog();
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
