package com.example.pcdashboard.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.View.IClassView;

import java.util.ArrayList;

import static com.example.pcdashboard.Manager.IScreenManager.COMMENT_DIALOG;
import static com.example.pcdashboard.Manager.IScreenManager.EDIT_FRAGMENT;
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
    private RelativeLayout rlInput;
    private int count=10;

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
        presenter.onRequestDatabase();
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
        rlInput=view.findViewById(R.id.rl_input_class);
        classAdapter = new ClassAdapter(getContext(),new ArrayList<ClassPost>(),this);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvInput.setOnClickListener(this);
        ivAvatar.setOnClickListener(this);
        swipeView.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorCold),getActivity().getResources().getColor(R.color.colorHot),getActivity().getResources().getColor(R.color.colorCold));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>100&&rlInput.getVisibility()==View.VISIBLE){
                    rlInput.setVisibility(View.GONE);
                    rlInput.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_top));
                }
                if(dy<-100&&rlInput.getVisibility()==View.GONE){
                    rlInput.setVisibility(View.VISIBLE);
                    rlInput.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_top));
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void onCommentClick(ClassPost classPost) {
        SharedPreferencesUtils.saveClassPost(getContext(),classPost);
        screenManager.openDialog(COMMENT_DIALOG,null);
    }

    @Override
    public void onEditClick(ClassPost classPost) {
        SharedPreferencesUtils.saveClassPost(getContext(),classPost);
        screenManager.openFeatureScreen(EDIT_FRAGMENT);
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
    public void onSuccessDatabase(ArrayList<ClassPost> classPosts) {
        classAdapter.update(classPosts);
        classAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessServer(ArrayList<ClassPost> classPosts) {
        classAdapter.update(classPosts);
        classAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(),"Tải thất bại\nVui lòng kiểm tra kết nối",CustomToast.LENGTH_SHORT,CustomToast.FAILURE);
    }

    @Override
    public void onDeleteSuccess() {
        CustomToast.makeText(getContext(),"Xóa bài thành công",CustomToast.LENGTH_SHORT,CustomToast.SUCCESS);
        presenter.onRequestServer(10);
    }

    @Override
    public void onDeleteFailure() {
        CustomToast.makeText(getContext(),"Xóa bài thất bại",CustomToast.LENGTH_SHORT,CustomToast.FAILURE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_input_class:
                screenManager.openFeatureScreen(POST_FRAGMENT);
                break;
            case R.id.iv_avatar_class:
                screenManager.openDialog(INFO_DIALOG, SharedPreferencesUtils.loadSelf(getContext()));
                break;
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRequestServer(count+=10);
        swipeView.setRefreshing(false);
    }
}