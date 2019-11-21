package com.example.pcdashboard.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pcdashboard.BuildConfig;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.R;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_ACCOUNT;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private ImageButton ibBack;
    private TextView tvVersion;
    private EditText etContent;
    private Button btnSend;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        tvVersion = view.findViewById(R.id.tv_version_feedback);
        etContent = view.findViewById(R.id.et_content_feedback);
        tvVersion.setText("Phiên bản " + BuildConfig.VERSION_NAME);
        ibBack = view.findViewById(R.id.ib_back_feedback);
        btnSend = view.findViewById(R.id.btn_send_feedback);
        btnSend.setOnClickListener(this);
        ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_feedback:
                ibBack.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                SharedPreferencesUtils.saveTabId(getContext(), TAB_ACCOUNT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.btn_send_feedback:
                btnSend.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                if (!TextUtils.isEmpty(etContent.getText()))
                    sendEmail(etContent.getText().toString());
                else
                    CustomToast.makeText(getContext(), "Nội dung phản hồi không được trống", CustomToast.LENGTH_SHORT, CustomToast.WARNING).show();

        }
    }

    private void sendEmail(String content) {
        Intent emailIntent = new Intent((Intent.ACTION_SEND));
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pcdashboard@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Phản hồi về P&C Dashboard");
        emailIntent.putExtra(Intent.EXTRA_TEXT, content);
        if (emailIntent.resolveActivity(getActivity().getPackageManager()) != null)
            startActivity(Intent.createChooser(emailIntent, SharedPreferencesUtils.loadSelf(getContext()).getName() + " gửi email bằng?"));
    }
}
