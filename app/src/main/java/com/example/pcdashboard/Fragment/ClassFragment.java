package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.ClassAdapter;
import com.example.pcdashboard.Dialog.CommentDialog;
import com.example.pcdashboard.Dialog.InfoDialog;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment implements ClassAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private ClassAdapter classAdapter;

    public ClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        initiazlie(view);
        return view;
    }

    private void initiazlie(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_class);
        classAdapter = new ClassAdapter(getContext(), createList(),this);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<ClassPost> createList() {
        ArrayList<ClassPost> list = new ArrayList();
        list.add(new ClassPost("11", "10:30, 20/11/2019", "Báo bài tuần học thứ 3...", "https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-9/17904352_875857065910879_4253602225817849277_n.jpg?_nc_cat=110&_nc_oc=AQm1MrCThCtzjxgxXKxo54CrWFGycLtU51zQ2bW2hfUsNscSrWP4-sKa_kNytJdSl2A&_nc_ht=scontent.fsgn5-3.fna&oh=095cde13bcb5369e30f9218887ed336c&oe=5DF1F8F6", "123", "Phan Thanh Tùng", "https://scontent.fsgn5-6.fna.fbcdn.net/v/t1.0-9/27752575_1979468902380047_2699760789037798279_n.jpg?_nc_cat=106&_nc_oc=AQms_a7K86SKOmLL3ZbRGwUJe5B47BjCC8Nqcd_u_lgd53KmB6UHU3Hvk_Q2Rwi1BjQ&_nc_ht=scontent.fsgn5-6.fna&oh=db9ca3164b98c4b862c9badfd4af017b&oe=5E39FC47"));
        list.add(new ClassPost("11", "10:30, 20/11/2019", "Báo bài tuần học thứ 3...", "https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-9/17904352_875857065910879_4253602225817849277_n.jpg?_nc_cat=110&_nc_oc=AQm1MrCThCtzjxgxXKxo54CrWFGycLtU51zQ2bW2hfUsNscSrWP4-sKa_kNytJdSl2A&_nc_ht=scontent.fsgn5-3.fna&oh=095cde13bcb5369e30f9218887ed336c&oe=5DF1F8F6", "123", "Phan Thanh Tùng", "https://scontent.fsgn5-6.fna.fbcdn.net/v/t1.0-9/27752575_1979468902380047_2699760789037798279_n.jpg?_nc_cat=106&_nc_oc=AQms_a7K86SKOmLL3ZbRGwUJe5B47BjCC8Nqcd_u_lgd53KmB6UHU3Hvk_Q2Rwi1BjQ&_nc_ht=scontent.fsgn5-6.fna&oh=db9ca3164b98c4b862c9badfd4af017b&oe=5E39FC47"));
        list.add(new ClassPost("11", "10:30, 20/11/2019", "Báo bài tuần học thứ 3...", "https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-9/17904352_875857065910879_4253602225817849277_n.jpg?_nc_cat=110&_nc_oc=AQm1MrCThCtzjxgxXKxo54CrWFGycLtU51zQ2bW2hfUsNscSrWP4-sKa_kNytJdSl2A&_nc_ht=scontent.fsgn5-3.fna&oh=095cde13bcb5369e30f9218887ed336c&oe=5DF1F8F6", "123", "Phan Thanh Tùng", "https://scontent.fsgn5-6.fna.fbcdn.net/v/t1.0-9/27752575_1979468902380047_2699760789037798279_n.jpg?_nc_cat=106&_nc_oc=AQms_a7K86SKOmLL3ZbRGwUJe5B47BjCC8Nqcd_u_lgd53KmB6UHU3Hvk_Q2Rwi1BjQ&_nc_ht=scontent.fsgn5-6.fna&oh=db9ca3164b98c4b862c9badfd4af017b&oe=5E39FC47"));
        list.add(new ClassPost("11", "10:30, 20/11/2019", "Báo bài tuần học thứ 3...", "https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-9/17904352_875857065910879_4253602225817849277_n.jpg?_nc_cat=110&_nc_oc=AQm1MrCThCtzjxgxXKxo54CrWFGycLtU51zQ2bW2hfUsNscSrWP4-sKa_kNytJdSl2A&_nc_ht=scontent.fsgn5-3.fna&oh=095cde13bcb5369e30f9218887ed336c&oe=5DF1F8F6", "123", "Phan Thanh Tùng", "https://scontent.fsgn5-6.fna.fbcdn.net/v/t1.0-9/27752575_1979468902380047_2699760789037798279_n.jpg?_nc_cat=106&_nc_oc=AQms_a7K86SKOmLL3ZbRGwUJe5B47BjCC8Nqcd_u_lgd53KmB6UHU3Hvk_Q2Rwi1BjQ&_nc_ht=scontent.fsgn5-6.fna&oh=db9ca3164b98c4b862c9badfd4af017b&oe=5E39FC47"));
        return list;
    }

    @Override
    public void onClick(ClassPost classPost) {
        CommentDialog dialog=new CommentDialog();
        dialog.show(getFragmentManager(),"comment dialog");
    }
}
