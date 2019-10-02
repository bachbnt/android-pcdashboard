package com.example.pcdashboard.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.AccountPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Utility.SharedPreferencesUtil;
import com.example.pcdashboard.View.IAccountView;

import static com.example.pcdashboard.Manager.IScreenManager.INFO_DIALOG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements IAccountView,View.OnClickListener {
    private ScreenManager screenManager;
    private AccountPresenter presenter;
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvId;
    private RelativeLayout rlInfo;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initialize(view);
        Log.i("tag","role "+ SharedPreferencesUtil.loadSelf(getContext()).getName());
        Log.i("tag","role "+ SharedPreferencesUtil.loadSelf(getContext()).getRole());
        return view;
    }

    @Override
    public void onResume() {
        presenter.setAccountView(this);
        presenter.loadSelf();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setAccountView(null);
        super.onPause();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter=new AccountPresenter(getContext());
        ivAvatar=view.findViewById(R.id.iv_avatar_account);
        tvName=view.findViewById(R.id.tv_name_account);
        tvId=view.findViewById(R.id.tv_id_account);
        rlInfo=view.findViewById(R.id.rl_info_account);
        rlInfo.setOnClickListener(this);
    }

    @Override
    public void onUpdate(User self) {
        Glide.with(getContext()).load(Uri.parse(self.getAvatar())).centerCrop().override(80,80).into(ivAvatar);
        tvName.setText(self.getName());
        tvId.setText(self.getId());
    }

    @Override
    public void showInfoDialog() {
        screenManager.openDialog(INFO_DIALOG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_info_account:
                showInfoDialog();
                break;
        }
    }
}
