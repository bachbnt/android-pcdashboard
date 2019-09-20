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
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.ILoginView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements ILoginView, View.OnClickListener {
    private ScreenManager screenManager;
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

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
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
                Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
                screenManager.openLoginScreen(0);
                break;
            case R.id.tv_forgot_login:
                Toast.makeText(getContext(), "Forgot", Toast.LENGTH_SHORT).show();
                screenManager.openLoginScreen(2);
                break;
        }
    }

    @Override
    public void onLogin() {

    }
}
