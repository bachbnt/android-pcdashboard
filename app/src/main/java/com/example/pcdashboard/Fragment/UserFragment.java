package com.example.pcdashboard.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pcdashboard.Adapter.UserAdapter;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Presenter.UserPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IUserView;

import java.util.ArrayList;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.INFO_DIALOG;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_CONTACT;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements IUserView,UserAdapter.OnItemCLickListener,View.OnClickListener {
    private ScreenManager screenManager;
    private UserPresenter presenter;
    private UserAdapter userAdapter;
    private RecyclerView recyclerView;
    private ImageButton ibBack;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user, container, false);
        initialize(view);
        return view;
    }
    @Override
    public void onResume() {
        presenter.setUserView(this);
        presenter.addUserListener();
        presenter.onRequestDatabase();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setUserView(null);
        presenter.removeUserListener();
        super.onPause();
    }

    private void initialize(View view){
        screenManager=ScreenManager.getInstance();
        presenter=new UserPresenter(getContext());
        recyclerView=view.findViewById(R.id.recycler_view_user);
        ibBack=view.findViewById(R.id.ib_back_user);
        userAdapter=new UserAdapter(getContext(),new ArrayList<User>(),this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ibBack.setOnClickListener(this);
    }

    @Override
    public void onSuccess(ArrayList<User> users) {
        userAdapter.update(users);
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(), "Tải danh sách thất bại", CustomToast.LENGTH_SHORT, CustomToast.FAILURE).show();
    }

    @Override
    public void onClick(User user) {
        screenManager.openDialog(INFO_DIALOG, user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_user:
                SharedPreferencesUtils.saveTabId(getContext(), TAB_CONTACT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
        }
    }
}
