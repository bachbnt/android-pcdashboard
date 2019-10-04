package com.example.pcdashboard.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Adapter.ClassAdapter;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Presenter.ClassPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Utility.SharedPreferencesUtil;
import com.example.pcdashboard.View.IClassView;

import java.util.ArrayList;

import static com.example.pcdashboard.Manager.IScreenManager.COMMENT_DIALOG;
import static com.example.pcdashboard.Manager.IScreenManager.POST_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment implements ClassAdapter.OnItemClickListener, IClassView,View.OnClickListener {
    private ScreenManager screenManager;
    private RecyclerView recyclerView;
    private ClassAdapter classAdapter;
    private ClassPresenter presenter;
    private ImageView ivAvatar;
    private TextView tvInput;

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setClassView(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setClassView(null);
        super.onPause();
    }

    private void initialize(View view) {
        screenManager=ScreenManager.getInstance();
        presenter=new ClassPresenter(getContext());
        recyclerView = view.findViewById(R.id.recycler_view_class);
        ivAvatar=view.findViewById(R.id.iv_avatar_class);
        tvInput=view.findViewById(R.id.tv_input_class);
        Glide.with(getContext()).load(Uri.parse(SharedPreferencesUtil.loadSelf(getContext()).getAvatar())).centerCrop().override(40,40).into(ivAvatar);
        classAdapter = new ClassAdapter(getContext(),new ArrayList<ClassPost>(),this);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.onRequest();
        tvInput.setOnClickListener(this);
    }

    @Override
    public void onClick(ClassPost classPost) {
        SharedPreferencesUtil.savePost(getContext(),classPost);
        screenManager.openDialog(COMMENT_DIALOG);
    }

    @Override
    public void onUpdate(ArrayList<ClassPost> classPosts) {
        classAdapter.updateList(classPosts);
        classAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), "Tải danh sách thất bại\nVui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_input_class:
                screenManager.openFeatureScreen(POST_FRAGMENT);
                break;
        }
    }
}
