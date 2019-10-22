package com.example.pcdashboard.Services;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Model.DepartmentPost;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatService {
    private static ChatService chatService;
    private static IChatService iChatService;
    private Context context;
    private ChatService(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWebService.urlServer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iChatService = retrofit.create(IChatService.class);
    }

    public static ChatService getInstance(Context context) {
        if (chatService == null)
            chatService = new ChatService(context);
        return chatService;
    }
    public void getChatMessages() {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<ArrayList<ChatMessage>> call = iChatService.getChatMessages(token);
        try {
            call.enqueue(new Callback<ArrayList<ChatMessage>>() {
                @Override
                public void onResponse(Call<ArrayList<ChatMessage>> call, Response<ArrayList<ChatMessage>> response) {
                    ArrayList<ChatMessage> chatMessages = response.body();
                    Log.i("tag","getChatMessages "+chatMessages.size());
                }

                @Override
                public void onFailure(Call<ArrayList<ChatMessage>> call, Throwable t) {
                    Log.i("tag","getChatMessages "+t.toString());
               }
            });
        } catch (Exception e) {
            Log.e("Exception ", "Post Service getDepartmentPosts" + e.toString());
        }
    }

}
