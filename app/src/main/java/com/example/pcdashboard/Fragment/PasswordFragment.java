package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IPasswordView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends Fragment implements IPasswordView, View.OnClickListener, TextWatcher {
    private EditText etOld;
    private EditText etNew;
    private EditText etRetype;
    private Button btnChange;

    public PasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initialize(View view) {
        etOld = view.findViewById(R.id.et_old_password);
        etNew = view.findViewById(R.id.et_new_password);
        etRetype = view.findViewById(R.id.et_retype_password);
        btnChange = view.findViewById(R.id.btn_change_password);
        etNew.addTextChangedListener(this);
        etRetype.addTextChangedListener(this);
        btnChange.setOnClickListener(this);
    }


    @Override
    public void onCheckFailure() {

    }

    @Override
    public void onChangeSuccess() {

    }

    @Override
    public void onChangeFailure() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_password:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(etRetype.getText().toString().equals(etNew.getText().toString()))
            etRetype.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_key_cold_24dp,0,R.drawable.ic_check_24dp,0);
        else etRetype.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_key_cold_24dp,0,0,0);
    }
}
