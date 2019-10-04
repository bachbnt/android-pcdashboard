package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.IScreenManager;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Presenter.ForgotPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Utility.SharedPreferencesUtil;
import com.example.pcdashboard.View.IForgotView;

import static com.example.pcdashboard.Manager.IScreenManager.EMAIL_DIALOG;
import static com.example.pcdashboard.Manager.IScreenManager.LOADING_DIALOG;
import static com.example.pcdashboard.Manager.IScreenManager.LOGIN_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotFragment extends Fragment implements View.OnClickListener, IForgotView {
    private ScreenManager screenManager;
    private ForgotPresenter presenter;
    private EditText etAccount;
    private Button btnGetPassword;
    private TextView tvBack;

    public ForgotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setForgotView(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setForgotView(null);
        super.onPause();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter=new ForgotPresenter(getContext());
        etAccount = view.findViewById(R.id.et_account_forgot);
        btnGetPassword = view.findViewById(R.id.btn_get_forgot);
        tvBack = view.findViewById(R.id.tv_back_forgot);
        btnGetPassword.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_forgot:
                presenter.onCheck(etAccount.getText().toString());
                break;
            case R.id.tv_back_forgot:
                screenManager.openLoginScreen(LOGIN_FRAGMENT);
                break;
        }
    }

    @Override
    public void showLoadingDialog() {
        screenManager.openDialog(LOADING_DIALOG);
    }

    @Override
    public void onGetSuccess() {
        screenManager.closeDialog(LOADING_DIALOG);
        Log.i("tag","onGetSuccess "+ SharedPreferencesUtil.loadEmail(getContext()));
        screenManager.openDialog(EMAIL_DIALOG);
    }

    @Override
    public void onCheckFailure() {
        Toast.makeText(getContext(), "Tài khoản không được để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetFailure() {
        screenManager.closeDialog(LOADING_DIALOG);
        Toast.makeText(getContext(), "Lấy lại mật khẩu thất bại\nVui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
    }
}
