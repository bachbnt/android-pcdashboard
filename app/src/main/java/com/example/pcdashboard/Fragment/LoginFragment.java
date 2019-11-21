package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Presenter.LoginPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.ILoginView;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_ACTIVITY;
import static com.example.pcdashboard.Manager.IScreenManager.FORGOT_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.LOADING_DIALOG;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements ILoginView, View.OnClickListener {
    private ScreenManager screenManager;
    LoginPresenter presenter;
    private EditText etAccount;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvForgot;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setLoginView(this);
        presenter.addLoginListener();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setLoginView(null);
        presenter.removeLoginListener();
        super.onPause();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter = new LoginPresenter(getContext());
        etAccount = view.findViewById(R.id.et_account_login);
        etPassword = view.findViewById(R.id.et_password_login);
        btnLogin = view.findViewById(R.id.btn_login_login);
        tvForgot = view.findViewById(R.id.tv_forgot_login);
        btnLogin.setOnClickListener(this);
        tvForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                btnLogin.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                presenter.onCheck(etAccount.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.tv_forgot_login:
                tvForgot.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                screenManager.openLoginScreen(FORGOT_FRAGMENT);
                break;
        }
    }

    @Override
    public void showLoadingDialog() {
        screenManager.openDialog(LOADING_DIALOG,null);
    }

    @Override
    public void onLoginSuccess() {
        screenManager.closeDialog(LOADING_DIALOG);
        presenter.changeLoginStatus(true);
        screenManager.openDashboardScreen(DASHBOARD_ACTIVITY);
    }

    @Override
    public void onCheckFailure() {
        CustomToast.makeText(getContext(), "Tài khoản hoặc mật khẩu\nkhông được để trống", CustomToast.LENGTH_SHORT,CustomToast.WARNING).show();
    }

    @Override
    public void onLoginFailure() {
        screenManager.closeDialog(LOADING_DIALOG);
        CustomToast.makeText(getContext(), "Đăng nhập thất bại\nVui lòng kiểm tra lại", CustomToast.LENGTH_SHORT,CustomToast.FAILURE).show();
    }
}
