package com.example.pcdashboard.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.pcdashboard.Manager.DatabaseHelper;
import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Services.ContactService;
import com.example.pcdashboard.View.IChatView;

import java.util.ArrayList;

interface IChatPresenter {
    void onRequestDatabase();

    void onRequestServer(int number);

    void onResponse(ArrayList<ChatMessage> chatMessages);
}

public class ChatPresenter implements ContactService.ChatListener, IChatPresenter {
    class ChatTask extends AsyncTask<String, Void, ArrayList<ChatMessage>> {

        @Override
        protected ArrayList<ChatMessage> doInBackground(String... strings) {
            ArrayList<ChatMessage> chatMessages = databaseHelper.loadChatMessages();
            return chatMessages;
        }

        @Override
        protected void onPostExecute(ArrayList<ChatMessage> chatMessages) {
            super.onPostExecute(chatMessages);
            if (chatMessages != null) {
                onResponse(chatMessages);
            }
            onRequestServer(20);
        }
    }

    private Context context;
    private IChatView view;
    private ContactService contactService;
    private DatabaseHelper databaseHelper;

    public ChatPresenter(Context context) {
        this.context = context;
        contactService = ContactService.getInstance(context);
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void setChatView(IChatView iChatView) {
        this.view = iChatView;
    }

    public void addChatListener() {
        contactService.setChatListener(this);
    }

    public void removeChatListener() {
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
    public void onRequestDatabase() {
        ChatTask chatTask=new ChatTask();
        chatTask.execute();
    }

    @Override
    public void onResponse(ArrayList<ChatMessage> chatMessages) {
        view.onSuccess(chatMessages);
    }

    @Override
    public void onRequestServer(int number) {
        contactService.getChatMessages(number);
    }

}
