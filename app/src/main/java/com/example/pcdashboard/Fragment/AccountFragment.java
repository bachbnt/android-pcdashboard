package com.example.pcdashboard.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.AccountPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.View.IAccountView;

import static com.example.pcdashboard.Manager.IScreenManager.EXAM_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.FEEDBACK_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.GUIDE_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.INFO_DIALOG;
import static com.example.pcdashboard.Manager.IScreenManager.INFO_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.LOGIN_ACTIVITY;
import static com.example.pcdashboard.Manager.IScreenManager.PASSWORD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.SCHEDULE_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements IAccountView, View.OnClickListener {
    private ScreenManager screenManager;
    private AccountPresenter presenter;
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvId;
    private RelativeLayout rlInfo;
    private LinearLayout llStudy, llHelp, llSetting;
    private TextView tvStudy, tvHelp, tvSetting,tvLogout;
    private boolean isStudy = false, isHelp = false, isSetting = false;
    private TextView tvSchedule,tvExam;
    private TextView tvGuide,tvFeedback;
    private TextView tvInfo,tvPassword;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setAccountView(this);
        presenter.onInit();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setAccountView(null);
        super.onPause();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter = new AccountPresenter(getContext());
        ivAvatar = view.findViewById(R.id.iv_avatar_account);
        tvName = view.findViewById(R.id.tv_name_account);
        tvId = view.findViewById(R.id.tv_id_account);
        rlInfo = view.findViewById(R.id.rl_info_account);
        llStudy = view.findViewById(R.id.ll_study_account);
        llHelp = view.findViewById(R.id.ll_help_account);
        llSetting = view.findViewById(R.id.ll_setting_account);
        tvStudy = view.findViewById(R.id.tv_study_account);
        tvHelp = view.findViewById(R.id.tv_help_account);
        tvSetting = view.findViewById(R.id.tv_setting_account);
        tvSchedule=view.findViewById(R.id.tv_schedule_account);
        tvExam=view.findViewById(R.id.tv_exam_account);
        tvGuide=view.findViewById(R.id.tv_guide_account);
        tvFeedback=view.findViewById(R.id.tv_feedback_account);
        tvInfo=view.findViewById(R.id.tv_info_account);
        tvPassword=view.findViewById(R.id.tv_password_account);
        tvLogout=view.findViewById(R.id.tv_logout_account);
        rlInfo.setOnClickListener(this);
        tvStudy.setOnClickListener(this);
        tvHelp.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvSchedule.setOnClickListener(this);
        tvExam.setOnClickListener(this);
        tvGuide.setOnClickListener(this);
        tvFeedback.setOnClickListener(this);
        tvInfo.setOnClickListener(this);
        tvPassword.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
    }

    @Override
    public void onInit(User self) {
        Glide.with(getContext()).load(Uri.parse(self.getAvatar())).centerCrop().override(80, 80).into(ivAvatar);
        tvName.setText(self.getName());
        tvId.setText(self.getId());
    }

    @Override
    public void showInfoDialog() {
        screenManager.openDialog(INFO_DIALOG, SharedPreferencesUtils.loadSelf(getContext()));
    }

    @Override
    public void selectMenu(String layoutName) {
        switch (layoutName) {
            case STUDY_LAYOUT:
                isStudy = !isStudy;
                if (isStudy) {
                    llStudy.setVisibility(View.VISIBLE);
                    llStudy.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_top_fade_in));
                    tvStudy.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_study_hot_32dp, 0, R.drawable.ic_up_hot_32dp, 0);
                } else {
                    llStudy.setVisibility(View.GONE);
                    llStudy.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_top_fade_out));
                    tvStudy.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_study_hot_32dp, 0, R.drawable.ic_down_hot_32dp, 0);
                }
                break;
            case HELP_LAYOUT:
                isHelp = !isHelp;
                if (isHelp) {
                    llHelp.setVisibility(View.VISIBLE);
                    llHelp.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_top_fade_in));
                    tvHelp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_help_hot_32dp, 0, R.drawable.ic_up_hot_32dp, 0);
                } else {
                    llHelp.setVisibility(View.GONE);
                    llHelp.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_top_fade_out));
                    tvHelp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_help_hot_32dp, 0, R.drawable.ic_down_hot_32dp, 0);
                }
                break;
            case SETTING_LAYOUT:
                isSetting = !isSetting;
                if (isSetting) {
                    llSetting.setVisibility(View.VISIBLE);
                    llSetting.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_top_fade_in));
                    tvSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_settings_hot_32dp, 0, R.drawable.ic_up_hot_32dp, 0);
                } else {
                    llSetting.setVisibility(View.GONE);
                    llSetting.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_top_fade_out));
                    tvSetting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_settings_hot_32dp, 0, R.drawable.ic_down_hot_32dp, 0);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_info_account:
                showInfoDialog();
                break;
            case R.id.tv_study_account:
                selectMenu(STUDY_LAYOUT);
                break;
            case R.id.tv_help_account:
                selectMenu(HELP_LAYOUT);
                break;
            case R.id.tv_setting_account:
                selectMenu(SETTING_LAYOUT);
                break;
            case R.id.tv_schedule_account:
                screenManager.openFeatureScreen(SCHEDULE_FRAGMENT);
                break;
            case R.id.tv_exam_account:
                screenManager.openFeatureScreen(EXAM_FRAGMENT);
                break;
            case R.id.tv_guide_account:
                screenManager.openFeatureScreen(GUIDE_FRAGMENT);
                break;
            case R.id.tv_feedback_account:
                screenManager.openFeatureScreen(FEEDBACK_FRAGMENT);
                break;
            case R.id.tv_info_account:
                screenManager.openFeatureScreen(INFO_FRAGMENT);
                break;
            case R.id.tv_password_account:
                screenManager.openFeatureScreen(PASSWORD_FRAGMENT);
                break;
            case R.id.tv_logout_account:
                presenter.onLogout();
                presenter.changeStatus(SharedPreferencesUtils.loadStatusLogin(getContext()));
                screenManager.openLoginScreen(LOGIN_ACTIVITY);
                break;
        }
    }
}
