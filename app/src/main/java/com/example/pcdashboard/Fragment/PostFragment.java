package com.example.pcdashboard.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Presenter.PostPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Services.ImageFilePath;
import com.example.pcdashboard.View.IPostView;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment implements View.OnClickListener, IPostView {
    private static final int GALLERY_REQUEST_CODE = 100;
    private String imagePath = "";
    private ScreenManager screenManager;
    private PostPresenter presenter;
    private EditText etInput;
    private TextView tvPost;
    private ImageButton ibBack;
    private ImageButton ibPhoto;
    private ImageView ivImage;


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
                presenter.onPost(etInput.getText().toString(),imagePath);
                break;
            case R.id.ib_back_post:
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.ib_photo_post:
                pickFromGallery();
                break;

        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
        screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), "Thất bại, vui lòng thử lại", Toast.LENGTH_SHORT).show();
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
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            Uri uri = data.getData();
            imagePath = ImageFilePath.getPath(getContext(), data.getData());
            Log.i("tag","onActivityResult "+imagePath);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                ivImage.setImageBitmap(bitmap);
            } catch (IOException ignored) {
            }
        }
    }




}
