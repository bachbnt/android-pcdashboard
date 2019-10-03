package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.IAccountPresenter;

public interface IAccountView {
    String STUDY_LAYOUT="STUDY_LAYOUT";
    String HELP_LAYOUT="HELP_LAYOUT";
    String SETTING_LAYOUT="SETTING_LAYOUT";
    void onUpdate(User self);
    void showInfoDialog();
    void selectMenu(String layoutName);
}
