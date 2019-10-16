package com.example.pcdashboard.Dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Manager.SharedPreferencesUtil;

import static com.example.pcdashboard.Manager.IScreenManager.EMAIL_DIALOG;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailDialog extends DialogFragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private TextView tvEmail;
    private Button btnConfirm;


    public EmailDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_email, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onStart() {
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onStart();
    }

    private void initialize(View view) {
        screenManager=ScreenManager.getInstance();
        tvEmail = view.findViewById(R.id.tv_email_email_dialog);
        btnConfirm=view.findViewById(R.id.btn_confirm_email_dialog);
        tvEmail.setText(SharedPreferencesUtil.loadEmailForgot(getContext()));
        btnConfirm.setOnClickListener(this);
        setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirm_email_dialog:
                screenManager.closeDialog(EMAIL_DIALOG);
                SharedPreferencesUtil.clearEmailForgot(getContext());
                break;
        }
    }
}
