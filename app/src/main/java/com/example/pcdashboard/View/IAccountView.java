package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.User;

public interface IAccountView {
    String STUDY_LAYOUT="STUDY_LAYOUT";
    String HELP_LAYOUT="HELP_LAYOUT";
    String SETTING_LAYOUT="SETTING_LAYOUT";
    void onInit(User self);
    void showInfoDialog();
    void selectMenu(String layoutName);
}
