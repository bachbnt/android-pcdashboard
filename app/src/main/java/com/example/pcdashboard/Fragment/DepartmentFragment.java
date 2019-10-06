package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.DepartmentAdapter;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Presenter.DepartmentPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IDeparmentView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentFragment extends Fragment implements IDeparmentView {
    private ScreenManager screenManager;
    private DepartmentPresenter presenter;
    private RecyclerView recyclerView;
    private DepartmentAdapter departmentAdapter;


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
        departmentAdapter = new DepartmentAdapter(getContext(),new ArrayList<DepartmentPost>());
        recyclerView.setAdapter(departmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.onRequest();
    }


    @Override
    public void onUpdate(ArrayList<DepartmentPost> departmentPosts) {
        departmentAdapter.updateList(departmentPosts);
        departmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), "Tải bảng tin bộ môn thất bại\nVui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
    }
}
