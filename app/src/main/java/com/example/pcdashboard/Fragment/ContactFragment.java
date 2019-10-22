package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.MessageAdapter;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Services.ChatService;
import com.example.pcdashboard.Services.IChatService;
import com.example.pcdashboard.Services.IPostService;
import com.example.pcdashboard.Services.IWebService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_ACCOUNT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements View.OnClickListener, ChatService.ChatListener {
    private ScreenManager screenManager;
    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private EditText etInput;
    private ImageButton ibSend;
    private MessageAdapter messageAdapter;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        request();
        messageAdapter = new MessageAdapter(getContext(),new ArrayList<ChatMessage>());
        ibBack = view.findViewById(R.id.ib_back_contact);
        recyclerView = view.findViewById(R.id.recycler_view_contact);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        etInput = view.findViewById(R.id.et_input_contact);
        ibSend = view.findViewById(R.id.ib_send_contact);
        ibSend.setOnClickListener(this);
        ibBack.setOnClickListener(this);
    }

    private void request(){
        ChatService chatService=ChatService.getInstance(getContext());
        chatService.setListener(this);
        chatService.getChatMessages();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_contact:
                SharedPreferencesUtils.saveTabId(getContext(), TAB_ACCOUNT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.ib_send_contact:
                break;
        }
    }

    @Override
    public void onSuccess(ArrayList<ChatMessage> messages) {
        messageAdapter.update(messages);
        messageAdapter.notifyDataSetChanged();
    }
}
