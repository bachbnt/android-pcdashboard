package com.example.pcdashboard.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pcdashboard.Dialog.CommentDialog;
import com.example.pcdashboard.Dialog.EditDialog;
import com.example.pcdashboard.Dialog.InfoDialog;
import com.example.pcdashboard.Fragment.AccountFragment;
import com.example.pcdashboard.Fragment.ClassFragment;
import com.example.pcdashboard.Fragment.ChatFragment;
import com.example.pcdashboard.Fragment.ContactFragment;
import com.example.pcdashboard.Fragment.DashboardFragment;
import com.example.pcdashboard.Fragment.DepartmentFragment;
import com.example.pcdashboard.Fragment.DeveloperFragment;
import com.example.pcdashboard.Fragment.EditFragment;
import com.example.pcdashboard.Fragment.ExamFragment;
import com.example.pcdashboard.Fragment.FeedbackFragment;
import com.example.pcdashboard.Fragment.GuideFragment;
import com.example.pcdashboard.Fragment.InfoFragment;
import com.example.pcdashboard.Fragment.PasswordFragment;
import com.example.pcdashboard.Fragment.PostFragment;
import com.example.pcdashboard.Fragment.ScheduleFragment;
import com.example.pcdashboard.Fragment.SelectClassFragment;
import com.example.pcdashboard.Fragment.UserFragment;
import com.example.pcdashboard.Fragment.WebFragment;
import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Request.TokenRequest;
import com.example.pcdashboard.Services.IAccountService;
import com.example.pcdashboard.Services.IWebService;
import com.example.pcdashboard.Services.WebService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity implements IScreenManager {
    private ScreenManager screenManager;
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        initialize();
    }


    private void initialize() {
        screenManager = ScreenManager.getInstance();
        screenManager.setIScreenManager(this);
        screenManager.openFeatureScreen(DASHBOARD_FRAGMENT,null);
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
            case SELECT_CLASS_FRAGMENT:
                fragment=new SelectClassFragment();
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
    public void openFeatureScreen(String screenName,String classId) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        switch (screenName) {
            case DASHBOARD_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new DashboardFragment()).commit();
                break;
            case WEB_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard,new WebFragment()).commit();
                break;
            case POST_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new PostFragment()).commit();
                break;
            case EDIT_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new EditFragment()).commit();
                break;
            case INFO_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new InfoFragment()).commit();
                break;
            case PASSWORD_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new PasswordFragment()).commit();
                break;
            case SCHEDULE_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new ScheduleFragment()).commit();
                break;
            case EXAM_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new ExamFragment()).commit();
                break;
            case GUIDE_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new GuideFragment()).commit();
                break;
            case FEEDBACK_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new FeedbackFragment()).commit();
                break;
            case DEVELOPER_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new DeveloperFragment()).commit();
                break;
            case CHAT_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new ChatFragment()).commit();
                break;
            case USER_FRAGMENT:
                fragmentTransaction.replace(R.id.fl_container_dashboard, new UserFragment(classId)).commit();
                break;
        }
    }

    @Override
    public void openDialog(String dialogName, User user) {
        switch (dialogName) {
            case INFO_DIALOG:
                InfoDialog dialog = new InfoDialog(user);
                dialog.show(getFragmentManager(), "info dialog");
                break;
            case COMMENT_DIALOG:
                CommentDialog commentDialog = new CommentDialog();
                commentDialog.show(getSupportFragmentManager(), "comment dialog");
                break;
            case EDIT_DIALOG:
                EditDialog editDialog = new EditDialog();
                editDialog.show(getSupportFragmentManager(), "edit dialog");
                break;
        }
    }

    @Override
    public void closeDialog(String dialogName) {
        //NULL
    }

}