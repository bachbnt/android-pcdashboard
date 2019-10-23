package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Services.ContactService;
import com.example.pcdashboard.View.IChatView;
import com.example.pcdashboard.View.IForgotView;

import java.util.ArrayList;

interface IChatPresenter {
    void onRequest(int number);

    void onResponse(ArrayList<ChatMessage> chatMessages);
}

public class ChatPresenter implements ContactService.ChatListener,IChatPresenter {
    private Context context;
    private IChatView view;
    private ContactService contactService;

    public ChatPresenter(Context context) {
        this.context = context;
        contactService = ContactService.getInstance(context);
    }
    public void setChatView(IChatView iChatView){
        this.view=iChatView;
    }

    public void addChatListener(){
        contactService.setChatListener(this);
    }
    public void removeChatListener(){
        contactService.setChatListener(null);
    }

    @Override
    public void onSuccess(ArrayList<ChatMessage> chatMessages) {
        onResponse(chatMessages);
    }

    @Override
    public void onFailure() {
        view.onFailure();
    }

    @Override
    public void onRequest(int number) {
        contactService.getChatMessages(number);
    }

    @Override
    public void onResponse(ArrayList<ChatMessage> chatMessages) {
        view.onSuccess(chatMessages);
    }
}
