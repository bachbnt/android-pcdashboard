package com.example.pcdashboard.Dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.CommentAdapter;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentDialog extends DialogFragment {
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;


    public CommentDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_comment, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_comment);
        commentAdapter = new CommentAdapter(getContext(), createList());
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private ArrayList<PostComment> createList() {
        ArrayList<PostComment> list = new ArrayList<>();
        list.add(new PostComment("111", "Tùng chỉ được cái xaolin là giỏi :)", "6:30, 30/9/2019", "1610000", "Nguyễn Hồng Sỹ Nguyên", "https://scontent.fsgn5-7.fna.fbcdn.net/v/t1.0-9/69260324_2375901956010113_4743259498271997952_n.jpg?_nc_cat=103&_nc_oc=AQnFrrXJvWb4Bli8RLrdrXQH3GzhnGBh-l6yk7MUpm5EMyV3cij5FLBPxg8-2HaM38c&_nc_ht=scontent.fsgn5-7.fna&oh=911f659f7a33502d1f3a3b7370cd7f2d&oe=5DF47001"));
        list.add(new PostComment("111", "Tùng chỉ được cái xaolin là giỏi :)", "6:30, 30/9/2019", "1610000", "Nguyễn Hồng Sỹ Nguyên", "https://scontent.fsgn5-7.fna.fbcdn.net/v/t1.0-9/69260324_2375901956010113_4743259498271997952_n.jpg?_nc_cat=103&_nc_oc=AQnFrrXJvWb4Bli8RLrdrXQH3GzhnGBh-l6yk7MUpm5EMyV3cij5FLBPxg8-2HaM38c&_nc_ht=scontent.fsgn5-7.fna&oh=911f659f7a33502d1f3a3b7370cd7f2d&oe=5DF47001"));
        list.add(new PostComment("111", "Tùng chỉ được cái xaolin là giỏi :)", "6:30, 30/9/2019", "1610000", "Nguyễn Hồng Sỹ Nguyên", "https://scontent.fsgn5-7.fna.fbcdn.net/v/t1.0-9/69260324_2375901956010113_4743259498271997952_n.jpg?_nc_cat=103&_nc_oc=AQnFrrXJvWb4Bli8RLrdrXQH3GzhnGBh-l6yk7MUpm5EMyV3cij5FLBPxg8-2HaM38c&_nc_ht=scontent.fsgn5-7.fna&oh=911f659f7a33502d1f3a3b7370cd7f2d&oe=5DF47001"));
        list.add(new PostComment("111", "Tùng chỉ được cái xaolin là giỏi :)", "6:30, 30/9/2019", "1610000", "Nguyễn Hồng Sỹ Nguyên", "https://scontent.fsgn5-7.fna.fbcdn.net/v/t1.0-9/69260324_2375901956010113_4743259498271997952_n.jpg?_nc_cat=103&_nc_oc=AQnFrrXJvWb4Bli8RLrdrXQH3GzhnGBh-l6yk7MUpm5EMyV3cij5FLBPxg8-2HaM38c&_nc_ht=scontent.fsgn5-7.fna&oh=911f659f7a33502d1f3a3b7370cd7f2d&oe=5DF47001"));
        list.add(new PostComment("111", "Tùng chỉ được cái xaolin là giỏi :)", "6:30, 30/9/2019", "1610000", "Nguyễn Hồng Sỹ Nguyên", "https://scontent.fsgn5-7.fna.fbcdn.net/v/t1.0-9/69260324_2375901956010113_4743259498271997952_n.jpg?_nc_cat=103&_nc_oc=AQnFrrXJvWb4Bli8RLrdrXQH3GzhnGBh-l6yk7MUpm5EMyV3cij5FLBPxg8-2HaM38c&_nc_ht=scontent.fsgn5-7.fna&oh=911f659f7a33502d1f3a3b7370cd7f2d&oe=5DF47001"));

        return list;
    }
}
