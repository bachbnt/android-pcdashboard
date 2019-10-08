package com.example.pcdashboard.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Presenter.PostPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IPostView;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment implements View.OnClickListener, IPostView {
    private ScreenManager screenManager;
    private PostPresenter presenter;
    private EditText etInput;
    private TextView tvPost;
    private ImageButton ibBack;


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
        tvPost.setOnClickListener(this);
        ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_post_post:
                presenter.onPost(etInput.getText().toString(),null);
                break;
            case R.id.ib_back_post:
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
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
}
