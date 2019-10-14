package com.example.pcdashboard.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.InfoPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IInfoView;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements IInfoView,View.OnClickListener {
    private ScreenManager screenManager;
    private InfoPresenter presenter;
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvId;
    private TextView tvClass;
    private EditText etEmail;
    private EditText etPhone;
    private Button btnUpdate;
    private ImageButton ibBack;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setInfoView(this);
        presenter.addInfoListener();
        presenter.onLoad();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setInfoView(null);
        presenter.removeInfoListener();
        super.onPause();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter = new InfoPresenter(getContext());
        ibBack=view.findViewById(R.id.ib_back_info);
        ivAvatar = view.findViewById(R.id.iv_avatar_info);
        tvName = view.findViewById(R.id.tv_name_info);
        tvId = view.findViewById(R.id.tv_id_info);
        tvClass = view.findViewById(R.id.tv_class_info);
        etEmail = view.findViewById(R.id.et_email_info);
        etPhone = view.findViewById(R.id.et_phone_info);
        btnUpdate=view.findViewById(R.id.btn_update_info);
        btnUpdate.setOnClickListener(this);
        ibBack.setOnClickListener(this);
    }

    @Override
    public void onLoad(User self) {
        Glide.with(getContext()).load(Uri.parse(self.getAvatar())).centerCrop().override(120, 120).into(ivAvatar);
        tvName.setText(self.getName());
        tvId.setText(self.getId());
        tvClass.setText(self.getClassId());
        etEmail.setText(self.getEmail());
        etPhone.setText(self.getPhone());
    }

    @Override
    public void onCheckFailure() {
        CustomToast.makeText(getContext(), "Email hoặc số điện thoại không được để trống", CustomToast.LENGTH_SHORT,CustomToast.WARNING).show();
    }

    @Override
    public void onUpdateSuccess() {
        CustomToast.makeText(getContext(), "Cập nhật thông tin thành công", CustomToast.LENGTH_SHORT,CustomToast.SUCCESS).show();
        screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
    }

    @Override
    public void onUpdateFailure() {
        CustomToast.makeText(getContext(), "Cập nhật thông tin thất bại\nVui lòng kiểm tra lại", CustomToast.LENGTH_SHORT,CustomToast.FAILURE).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update_info:
                presenter.onCheck(etEmail.getText().toString(),etPhone.getText().toString());
                break;
            case R.id.ib_back_info:
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
        }
    }
}
