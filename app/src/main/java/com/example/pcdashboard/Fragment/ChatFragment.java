package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.MessageAdapter;
import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Presenter.ChatPresenter;
import com.example.pcdashboard.R;
import com.example.pcdashboard.View.IChatView;

import java.util.ArrayList;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_CONTACT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements View.OnClickListener, IChatView {
    private ScreenManager screenManager;
    private ChatPresenter presenter;
    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private EditText etInput;
    private ImageButton ibSend;
    private MessageAdapter messageAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.setChatView(this);
        presenter.addChatListener();
        presenter.onRequestDatabase();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.setChatView(null);
        presenter.removeChatListener();
        super.onPause();
    }
    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        presenter=new ChatPresenter(getContext());
        messageAdapter = new MessageAdapter(getContext(),new ArrayList<ChatMessage>());
        ibBack = view.findViewById(R.id.ib_back_chat);
        recyclerView = view.findViewById(R.id.recycler_view_chat);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        etInput = view.findViewById(R.id.et_input_chat);
        ibSend = view.findViewById(R.id.ib_send_chat);
        ibSend.setOnClickListener(this);
        ibBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_chat:
                SharedPreferencesUtils.saveTabId(getContext(), TAB_CONTACT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.ib_send_chat:
                break;
        }
    }

    @Override
    public void onSuccess(ArrayList<ChatMessage> chatMessages) {
        messageAdapter.update(chatMessages);
        messageAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(chatMessages.size()-1);
    }

    @Override
    public void onFailure() {
        CustomToast.makeText(getContext(), "Tải tin nhắn thất bại", CustomToast.LENGTH_SHORT, CustomToast.FAILURE).show();
    }
}
