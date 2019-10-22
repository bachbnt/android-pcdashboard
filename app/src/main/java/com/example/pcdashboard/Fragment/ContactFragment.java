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
public class ContactFragment extends Fragment implements View.OnClickListener {
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
        messageAdapter = new MessageAdapter(getContext(), list());
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
        chatService.getChatMessages();
    }
    private ArrayList<ChatMessage> list() {
        ArrayList<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("Xin chao đây là test 1dssclsncjsdjcxcn csadicscnsklc csadc", "10:30", "1613013", "Bùi Ngô Tôn Bách", "https://scontent.fsgn3-1.fna.fbcdn.net/v/t1.0-9/31959431_893209754185124_1091169959473577984_n.jpg?_nc_cat=111&_nc_oc=AQnFiRyQaW1_m8dw-Cdy0-iF-djVibSDxjrVg7qRdfbpsTABQZ1ib3DbgnyYKPyVHyA&_nc_ht=scontent.fsgn3-1.fna&oh=23d7bdb8c55c92e0cf651b239151e95a&oe=5E2AAF36"));
        messages.add(new ChatMessage("Xin chao", "10:30", "1613124", "Nguyễn Hồng Sỹ Nguyên", "https://scontent.fsgn8-1.fna.fbcdn.net/v/t1.0-9/34847132_911306832375416_1030011785547087872_n.jpg?_nc_cat=108&_nc_oc=AQl1aMbpd4g2lD-WmR5Syl0cF-ExlOMOKgKhumza3WPKBB6wkgHCr1NOxVaon3dEooo&_nc_ht=scontent.fsgn8-1.fna&oh=0e5eb6163be27301239d04d427b7e6f7&oe=5E21EB91"));
        messages.add(new ChatMessage("Xin chao", "10:30", "1613013", "Bùi Ngô Tôn Bách", "https://scontent.fsgn3-1.fna.fbcdn.net/v/t1.0-9/31959431_893209754185124_1091169959473577984_n.jpg?_nc_cat=111&_nc_oc=AQnFiRyQaW1_m8dw-Cdy0-iF-djVibSDxjrVg7qRdfbpsTABQZ1ib3DbgnyYKPyVHyA&_nc_ht=scontent.fsgn3-1.fna&oh=23d7bdb8c55c92e0cf651b239151e95a&oe=5E2AAF36"));
        messages.add(new ChatMessage("Xin chao", "10:30", "1613124", "Nguyễn Hồng Sỹ Nguyên", "https://scontent.fsgn8-1.fna.fbcdn.net/v/t1.0-9/34847132_911306832375416_1030011785547087872_n.jpg?_nc_cat=108&_nc_oc=AQl1aMbpd4g2lD-WmR5Syl0cF-ExlOMOKgKhumza3WPKBB6wkgHCr1NOxVaon3dEooo&_nc_ht=scontent.fsgn8-1.fna&oh=0e5eb6163be27301239d04d427b7e6f7&oe=5E21EB91"));
        messages.add(new ChatMessage("Xin chao", "10:30", "1613124", "Phan Thanh Tùng", "https://scontent.fsgn4-1.fna.fbcdn.net/v/t1.0-9/37792860_961105654062200_7483956461463142400_n.jpg?_nc_cat=100&_nc_oc=AQmyR1smG6JbraFEvoteu0Ebwol1k-XtBdzwevhYfCZhMjVoCDSvQF27z0aSMtauU40&_nc_ht=scontent.fsgn4-1.fna&oh=08ae805bac9f3ae3345a6ae20dd91832&oe=5E2093C2"));
        return messages;
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
}
