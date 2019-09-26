package com.example.pcdashboard.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.AccountPresenter;
import com.example.pcdashboard.Presenter.IAccountPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IAccountView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements IAccountView {
    private ScreenManager screenManager;
    private AccountPresenter presenter;
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvId;


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
        presenter.onLoadSelf();
        super.onResume();
    }

    @Override
    public void onStop() {
        presenter.setAccountView(null);
        super.onStop();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter=new AccountPresenter(getContext());
        ivAvatar=view.findViewById(R.id.iv_avatar_account);
        tvName=view.findViewById(R.id.tv_name_account);
        tvId=view.findViewById(R.id.tv_id_account);
    }

    @Override
    public void onShowSelf(User self) {
        Glide.with(getContext()).load(Uri.parse(self.getAvatar())).into(ivAvatar);
        tvName.setText(self.getName());
        tvId.setText(self.getId());
    }
}
