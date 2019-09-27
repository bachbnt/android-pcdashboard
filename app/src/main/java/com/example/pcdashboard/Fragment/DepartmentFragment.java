package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.DepartmentAdapter;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentFragment extends Fragment {
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
        initiazlie(view);
        return view;
    }

    private void initiazlie(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_department);
        departmentAdapter = new DepartmentAdapter(getContext(),createList());
        recyclerView.setAdapter(departmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private ArrayList<DepartmentPost> createList(){
        ArrayList<DepartmentPost> list=new ArrayList();
        list.add(new DepartmentPost("111","Thông báo nhập học","10:30, 20/11/2019","Toàn bộ sinh viên sẽ nhập học vào...","https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-9/17904352_875857065910879_4253602225817849277_n.jpg?_nc_cat=110&_nc_oc=AQm1MrCThCtzjxgxXKxo54CrWFGycLtU51zQ2bW2hfUsNscSrWP4-sKa_kNytJdSl2A&_nc_ht=scontent.fsgn5-3.fna&oh=095cde13bcb5369e30f9218887ed336c&oe=5DF1F8F6"));
        list.add(new DepartmentPost("111","Thông báo nhập học","10:30, 20/11/2019","Toàn bộ sinh viên sẽ nhập học vào...","https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-9/17904352_875857065910879_4253602225817849277_n.jpg?_nc_cat=110&_nc_oc=AQm1MrCThCtzjxgxXKxo54CrWFGycLtU51zQ2bW2hfUsNscSrWP4-sKa_kNytJdSl2A&_nc_ht=scontent.fsgn5-3.fna&oh=095cde13bcb5369e30f9218887ed336c&oe=5DF1F8F6"));
        list.add(new DepartmentPost("111","Thông báo nhập học","10:30, 20/11/2019","Toàn bộ sinh viên sẽ nhập học vào...","https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-9/17904352_875857065910879_4253602225817849277_n.jpg?_nc_cat=110&_nc_oc=AQm1MrCThCtzjxgxXKxo54CrWFGycLtU51zQ2bW2hfUsNscSrWP4-sKa_kNytJdSl2A&_nc_ht=scontent.fsgn5-3.fna&oh=095cde13bcb5369e30f9218887ed336c&oe=5DF1F8F6"));
        list.add(new DepartmentPost("111","Thông báo nhập học","10:30, 20/11/2019","Toàn bộ sinh viên sẽ nhập học vào...","https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-9/17904352_875857065910879_4253602225817849277_n.jpg?_nc_cat=110&_nc_oc=AQm1MrCThCtzjxgxXKxo54CrWFGycLtU51zQ2bW2hfUsNscSrWP4-sKa_kNytJdSl2A&_nc_ht=scontent.fsgn5-3.fna&oh=095cde13bcb5369e30f9218887ed336c&oe=5DF1F8F6"));
        return list;
    }
}
