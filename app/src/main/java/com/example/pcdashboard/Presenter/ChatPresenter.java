package com.example.pcdashboard.Presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.pcdashboard.Manager.DatabaseHelper;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Services.ContactService;
import com.example.pcdashboard.Services.IWebService;
import com.example.pcdashboard.View.IChatView;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

interface IChatPresenter {
    void onRequestDatabase();

    void onRequestServer(int number);

    void onResponse(ArrayList<ChatMessage> chatMessages);

    void connectSocket();

    void listenSocket();

    void sendMessage(String content);

    void disconnectSocket();

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

    private Activity activity;
    private IChatView view;
    private ContactService contactService;
    private DatabaseHelper databaseHelper;
    private Socket socket;
    private ArrayList<ChatMessage> chatMessages;

    public ChatPresenter(Activity activity) {
        this.activity = activity;
        contactService = ContactService.getInstance(activity);
        databaseHelper = DatabaseHelper.getInstance(activity);
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
        this.chatMessages=chatMessages;
        onResponse(chatMessages);
    }

    @Override
    public void onFailure() {
        if(view!=null)
        view.onFailure();
    }

    @Override
    public void onRequestDatabase() {
        ChatTask chatTask=new ChatTask();
        chatTask.execute();
    }

    @Override
    public void onResponse(ArrayList<ChatMessage> chatMessages) {
        if(view!=null)
            view.onSuccess(chatMessages);
    }

    @Override
    public void connectSocket() {
        try {
            socket= IO.socket(IWebService.urlSocket);
            socket.connect();
            socket.emit("join", SharedPreferencesUtils.loadSelf(activity).getClassId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listenSocket() {
        socket.on("message", args -> activity.runOnUiThread(() -> {
            JSONObject data=(JSONObject)args[0];
            ChatMessage chatMessage=new ChatMessage();
            try {
                chatMessage.setUserId(data.getString("userId"));
                chatMessage.setUserAvatar(data.getString("userAvatar"));
                chatMessage.setUserName(data.getString("userName"));
                chatMessage.setContent(data.getString("content"));
                chatMessage.setTime(data.getString("time"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            chatMessages.add(chatMessage);
            onResponse(chatMessages);
            Log.i("tag","listenSocket "+chatMessages.size());
        }));
    }

    @Override
    public void sendMessage(String content) {
        String token = SharedPreferencesUtils.loadToken(activity).getTokenType() + " " + SharedPreferencesUtils.loadToken(activity).getAccessToken();
        socket.emit("haveMessage", content,token);
    }

    @Override
    public void disconnectSocket() {
        socket.disconnect();
    }

    @Override
    public void onRequestServer(int number) {
        contactService.getChatMessages(number);
    }
}
