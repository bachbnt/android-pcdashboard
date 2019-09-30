package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Presenter.LoginPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.ILoginView;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_ACTIVITY;
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
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setLoginView(null);
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
                onInput();
                break;
            case R.id.tv_forgot_login:
                break;
        }
    }

    @Override
    public void onInput() {
        presenter.onCheck(etAccount.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void showLoadingDialog() {
        screenManager.openDialog(LOADING_DIALOG);
    }

    @Override
    public void onUpdateScreen() {
        screenManager.closeDialog(LOADING_DIALOG);
        screenManager.openLoginScreen(DASHBOARD_ACTIVITY);
    }

    @Override
    public void onCheckFailure() {
        Toast.makeText(getContext(), "Tài khoản hoặc Mật khẩu\nkhông được để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure() {
        screenManager.closeDialog(LOADING_DIALOG);
        Toast.makeText(getContext(), "Đăng nhập thất bại\nVui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
    }
}
