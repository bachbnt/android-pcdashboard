package com.example.pcdashboard.Fragment;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.PostPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Manager.ImageFilePath;
import com.example.pcdashboard.View.IPostView;

import java.io.IOException;

import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_CLASS;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment implements View.OnClickListener, IPostView {
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    private static final int GALLERY_REQUEST_CODE = 100;
    private String imagePath;
    private ScreenManager screenManager;
    private PostPresenter presenter;
    private EditText etInput;
    private TextView tvPost;
    private ImageButton ibBack;
    private ImageButton ibPhoto;
    private ImageView ivImage;
    private ImageView ivAvatar;
    private TextView tvClass;
    private TextView tvName;


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_post, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setPostView(this);
        presenter.addPostListener();
        presenter.onInit();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setPostView(null);
        presenter.removePostListener();
        super.onPause();
    }

    private void initialize(View view){
        screenManager=ScreenManager.getInstance();
        presenter=new PostPresenter(getContext());
        tvClass=view.findViewById(R.id.tv_class_post);
        tvName=view.findViewById(R.id.tv_name_post);
        ivAvatar=view.findViewById(R.id.iv_avatar_post);
        etInput=view.findViewById(R.id.et_input_post);
        tvPost=view.findViewById(R.id.tv_post_post);
        ibBack=view.findViewById(R.id.ib_back_post);
        ibPhoto=view.findViewById(R.id.ib_photo_post);
        ivImage=view.findViewById(R.id.iv_image_post);
        ibPhoto.setOnClickListener(this);
        tvPost.setOnClickListener(this);
        ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_post_post:
                if (EasyPermissions.hasPermissions(getContext(), galleryPermissions)) {
                    presenter.onCheck(etInput.getText().toString().trim(),imagePath);
                    tvPost.setEnabled(false);
                } else {
                    EasyPermissions.requestPermissions(this, "Hãy cho phép truy cập bộ nhớ thiết bị", 101, galleryPermissions);
                }
                break;
            case R.id.ib_back_post:
                SharedPreferencesUtils.saveTabId(getContext(),TAB_CLASS);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT,null);
                break;
            case R.id.ib_photo_post:
                pickFromGallery();
                break;

        }
    }

    @Override
    public void onInit(User self) {
        Glide.with(getContext()).load(Uri.parse(self.getAvatar())).centerCrop().override(50,50).into(ivAvatar);
        tvName.setText(self.getName());
        tvClass.setText("Thành viên của "+self.getClassId());
    }

    @Override
    public void onCheckFailure() {
        CustomToast.makeText(getContext(), "Nội dung hoặc hình ảnh không được trống", CustomToast.LENGTH_SHORT,CustomToast.WARNING).show();
    }

    @Override
    public void onSuccess() {
        CustomToast.makeText(getContext(), "Thành công", CustomToast.LENGTH_SHORT,CustomToast.SUCCESS).show();
        tvPost.setEnabled(true);
        SharedPreferencesUtils.saveTabId(getContext(),TAB_CLASS);
        screenManager.openFeatureScreen(DASHBOARD_FRAGMENT,null);
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(), "Thất bại\nVui lòng thử lại", CustomToast.LENGTH_SHORT,CustomToast.FAILURE).show();
        tvPost.setEnabled(true);
    }
    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(Intent.createChooser(intent,SharedPreferencesUtils.loadSelf(getContext()).getName() + " chọn ảnh bằng?"),GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            Uri uri = data.getData();
            imagePath = ImageFilePath.getPath(getContext(), data.getData());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                ivImage.setImageBitmap(bitmap);
            } catch (IOException ignored) {
            }
        }
    }




}
