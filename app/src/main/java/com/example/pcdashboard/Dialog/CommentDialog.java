package com.example.pcdashboard.Dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.CommentAdapter;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Presenter.CommentPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.View.ICommentView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentDialog extends DialogFragment implements ICommentView,View.OnClickListener,CommentAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private CommentPresenter presenter;
    private ImageButton ibSend;
    private EditText etInput;


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
        presenter.onRequest(SharedPreferencesUtil.loadPost(getContext()));
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
        ibSend=view.findViewById(R.id.ib_send_comment_dialog);
        etInput=view.findViewById(R.id.et_input_comment_dialog);
        commentAdapter = new CommentAdapter(getContext(), new ArrayList<PostComment>(),this);
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter=new CommentPresenter(getContext());
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ibSend.setOnClickListener(this);
    }


    @Override
    public void onUpdate(ArrayList<PostComment> postComments) {
        commentAdapter.updateList(postComments);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {
        presenter.onRequest(SharedPreferencesUtil.loadPost(getContext()));
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_send_comment_dialog:
                presenter.onCreate(etInput.getText().toString());
                etInput.setText("");
                break;
        }
    }

    @Override
    public void onEdit(PostComment postComment) {
        presenter.onEdit(postComment);
    }

    @Override
    public void onDelete(PostComment postComment) {
        presenter.onDelete(postComment);
    }
}
