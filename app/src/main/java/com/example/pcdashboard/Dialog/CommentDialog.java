package com.example.pcdashboard.Dialog;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcdashboard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentDialog extends Fragment {


    public CommentDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_comment, container, false);
    }

}
