package com.example.pcdashboard.Dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.CommentAdapter;
import com.example.pcdashboard.Fragment.ClassFragment;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Presenter.CommentPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Utility.SharedPreferencesUtil;
import com.example.pcdashboard.View.ICommentView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentDialog extends DialogFragment implements ICommentView {
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private CommentPresenter presenter;


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

    @Override
    public void onResume() {
        presenter.setClassView(this);
        presenter.addCommentListener();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setClassView(null);
        presenter.removeCommentListener();
        super.onPause();
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_comment);
        commentAdapter = new CommentAdapter(getContext(), new ArrayList<PostComment>());
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter=new CommentPresenter(getContext());
        presenter.onRequest(SharedPreferencesUtil.loadPost(getContext()));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    @Override
    public void onUpdate(ArrayList<PostComment> postComments) {
        commentAdapter.updateList(postComments);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), "Tải bình luận thất bại", Toast.LENGTH_SHORT).show();
    }
}
