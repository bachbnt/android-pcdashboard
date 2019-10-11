package com.example.pcdashboard.Dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoDialog extends DialogFragment {
    private User user;
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvId;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvClass;

    public InfoDialog(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_info, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        onInit();
        super.onResume();
    }

    private void initialize(View view) {
        ivAvatar=view.findViewById(R.id.iv_avatar_info_dialog);
        tvName=view.findViewById(R.id.tv_name_info_dialog);
        tvId=view.findViewById(R.id.tv_id_info_dialog);
        tvEmail=view.findViewById(R.id.tv_email_info_dialog);
        tvPhone=view.findViewById(R.id.tv_phone_info_dialog);
        tvClass=view.findViewById(R.id.tv_class_info_dialog);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void onInit(){
        Glide.with(getContext()).load(Uri.parse(user.getAvatar())).centerCrop().override(160,160).into(ivAvatar);
        tvName.setText(user.getName());
        tvId.setText(user.getId());
        tvEmail.setText(user.getEmail());
        tvPhone.setText(user.getPhone());
        tvClass.setText(user.getClassId());
    }
}
