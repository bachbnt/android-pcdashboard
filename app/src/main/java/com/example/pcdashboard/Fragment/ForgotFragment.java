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

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
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

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
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
                Toast.makeText(getContext(), "Get Password", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_back_forgot:
                Toast.makeText(getContext(), "Back", Toast.LENGTH_SHORT).show();
                screenManager.openLoginScreen(1);
                break;
        }
    }
}
