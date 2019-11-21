package com.example.pcdashboard.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.EditPostPostPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IEditPostView;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_CLASS;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment implements View.OnClickListener, IEditPostView {
    private ScreenManager screenManager;
    private EditPostPostPresenter presenter;
    private EditText etInput;
    private TextView tvEdit;
    private ImageButton ibBack;
    private TextView tvClass;
    private TextView tvName;
    private ImageView ivAvatar,ivImage;

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setEditView(this);
        presenter.addEditListener();
        presenter.onInit();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setEditView(null);
        presenter.removeEditListener();
        super.onPause();
    }

    @Override
    public void onStop() {
        SharedPreferencesUtils.clearClassPost(getContext());
        super.onStop();
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter = new EditPostPostPresenter(getContext());
        etInput = view.findViewById(R.id.et_input_edit);
        ibBack = view.findViewById(R.id.ib_back_edit);
        tvEdit = view.findViewById(R.id.tv_edit_edit);
        tvClass = view.findViewById(R.id.tv_class_edit);
        tvName = view.findViewById(R.id.tv_name_edit);
        ivAvatar = view.findViewById(R.id.iv_avatar_edit);
        ivImage=view.findViewById(R.id.iv_image_edit);
        ibBack.setOnClickListener(this);
        tvEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_edit:
                ibBack.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                SharedPreferencesUtils.saveTabId(getContext(), TAB_CLASS);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.tv_edit_edit:
                tvEdit.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                presenter.onCheck(etInput.getText().toString().trim());
                break;
        }
    }

    @Override
    public void onInit(User self, ClassPost classPost) {
        Glide.with(getContext()).load(Uri.parse(self.getAvatar())).centerCrop().override(50, 50).into(ivAvatar);
        tvName.setText(self.getName());
        if(SharedPreferencesUtils.loadSelf(getContext()).getRole().equals("ROLE_TEACHER"))
            tvClass.setText("Giảng viên");
        else tvClass.setText("Thành viên của " + self.getClassId());
        if (classPost.getImage() != null)
            Glide.with(getContext()).load(Uri.parse(classPost.getImage())).into(ivImage);
        etInput.setText(classPost.getContent());
    }

    @Override
    public void onCheckFailure() {
        CustomToast.makeText(getContext(), "Nội dung không được trống", CustomToast.LENGTH_SHORT,CustomToast.WARNING).show();
    }

    @Override
    public void onSuccess() {
        CustomToast.makeText(getContext(), "Thành công", CustomToast.LENGTH_SHORT,CustomToast.SUCCESS).show();
        tvEdit.setEnabled(true);
        SharedPreferencesUtils.saveTabId(getContext(),TAB_CLASS);
        screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(), "Thất bại\nVui lòng thử lại", CustomToast.LENGTH_SHORT,CustomToast.FAILURE).show();
        tvEdit.setEnabled(true);
    }
}
