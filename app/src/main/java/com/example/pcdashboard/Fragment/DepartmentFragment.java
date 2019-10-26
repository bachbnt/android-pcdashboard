package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.pcdashboard.Adapter.DepartmentAdapter;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.EndlessRecyclerViewScrollListener;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Presenter.DepartmentPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IDeparmentView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentFragment extends Fragment implements IDeparmentView, SwipeRefreshLayout.OnRefreshListener {
    private ScreenManager screenManager;
    private int numberOfPosts=10;
    private DepartmentPresenter presenter;
    private RecyclerView recyclerView;
    private DepartmentAdapter departmentAdapter;
    private SwipeRefreshLayout swipeView;


    public DepartmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_department, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setDepartmentView(this);
        presenter.addDepartmentListener();
        presenter.onRequestDatabase();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setDepartmentView(null);
        presenter.removeDepartmentListener();
        super.onPause();
    }

    private void initialize(View view) {
        screenManager=ScreenManager.getInstance();
        presenter=new DepartmentPresenter(getContext());
        recyclerView = view.findViewById(R.id.recycler_view_department);
        swipeView=view.findViewById(R.id.swipe_refresh_department);
        swipeView.setOnRefreshListener(this);
        departmentAdapter = new DepartmentAdapter(getContext(),new ArrayList<DepartmentPost>());
        recyclerView.setAdapter(departmentAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeView.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorCold),getActivity().getResources().getColor(R.color.colorHot),getActivity().getResources().getColor(R.color.colorCold));

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onRequestServer(numberOfPosts+=10);
            }
        });
    }


    @Override
    public void onSuccess(ArrayList<DepartmentPost> departmentPosts) {
        departmentAdapter.update(departmentPosts);
        departmentAdapter.notifyDataSetChanged();
    }


    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(), "Tải bản tin thất bại\nVui lòng kiểm tra kết nối", CustomToast.LENGTH_SHORT,CustomToast.FAILURE).show();
    }

    @Override
    public void onRefresh() {
        Log.i("tag","onRefreshing Department");
        presenter.onRequestServer(10);
        swipeView.setRefreshing(false);
    }
}
