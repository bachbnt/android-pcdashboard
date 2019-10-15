package com.example.pcdashboard.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Adapter.ClassAdapter;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.ClassPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.View.IClassView;

import java.util.ArrayList;

import static com.example.pcdashboard.Manager.IScreenManager.COMMENT_DIALOG;
import static com.example.pcdashboard.Manager.IScreenManager.INFO_DIALOG;
import static com.example.pcdashboard.Manager.IScreenManager.POST_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment implements ClassAdapter.OnItemClickListener, IClassView,View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ScreenManager screenManager;
    private RecyclerView recyclerView;
    private ClassAdapter classAdapter;
    private ClassPresenter presenter;
    private ImageView ivAvatar;
    private TextView tvInput;
    private SwipeRefreshLayout swipeView;

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
        presenter.addClassListener();
        presenter.onInit();
        presenter.onRequest(10);
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setClassView(null);
        presenter.removeClassListener();
        super.onPause();
    }

    private void initialize(View view) {
        screenManager=ScreenManager.getInstance();
        presenter=new ClassPresenter(getContext());
        recyclerView = view.findViewById(R.id.recycler_view_class);
        swipeView=view.findViewById(R.id.swipe_refresh_class);
        swipeView.setOnRefreshListener(this);
        ivAvatar=view.findViewById(R.id.iv_avatar_class);
        tvInput=view.findViewById(R.id.tv_input_class);
        classAdapter = new ClassAdapter(getContext(),new ArrayList<ClassPost>(),this);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvInput.setOnClickListener(this);
        ivAvatar.setOnClickListener(this);
    }

    @Override
    public void onCommentClick(ClassPost classPost) {
        SharedPreferencesUtil.savePostIdClass(getContext(),classPost);
        screenManager.openDialog(COMMENT_DIALOG,null);
    }

    @Override
    public void onEditClick(ClassPost classPost) {
        presenter.onEdit(classPost);
    }

    @Override
    public void onDeleteClick(ClassPost classPost) {
        presenter.onDelete(classPost);
    }

    @Override
    public void onInit(User self) {
        Glide.with(getContext()).load(Uri.parse(self.getAvatar())).centerCrop().override(40,40).into(ivAvatar);
    }

    @Override
    public void onUpdate(ArrayList<ClassPost> classPosts) {
        classAdapter.updateList(classPosts);
        classAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(),"Tải thất bại\nVui lòng kiểm tra lại",CustomToast.LENGTH_SHORT,CustomToast.FAILURE);
    }

    @Override
    public void onSuccess() {
        presenter.onRequest(10);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_input_class:
                screenManager.openFeatureScreen(POST_FRAGMENT);
                break;
            case R.id.iv_avatar_class:
                screenManager.openDialog(INFO_DIALOG,SharedPreferencesUtil.loadSelf(getContext()));
                break;
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRequest(10);
        swipeView.setRefreshing(false);
    }
}