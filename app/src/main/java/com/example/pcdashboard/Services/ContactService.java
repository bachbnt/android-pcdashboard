package com.example.pcdashboard.Services;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactService {
    private static ContactService contactService;
    private static IContactService iContactService;
    private Context context;
    private ChatListener chatListener;
    private UserListener userListener;

    private ContactService(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWebService.urlServer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iContactService = retrofit.create(IContactService.class);
    }

    public interface ChatListener {
        void onSuccess(ArrayList<ChatMessage> chatMessages);

        void onFailure();
    }

    public interface UserListener {
        void onSuccess(ArrayList<User> users);

        void onFailure();
    }

    public static ContactService getInstance(Context context) {
        if (contactService == null)
            contactService = new ContactService(context);
        return contactService;
    }

    public void setChatListener(ChatListener chatListener) {
        this.chatListener = chatListener;
    }

    public void setUserListener(UserListener userListener) {
        this.userListener = userListener;
    }


    public void getChatMessages(int number) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<ArrayList<ChatMessage>> call = iContactService.getChatMessages(token,number);
        try {
            call.enqueue(new Callback<ArrayList<ChatMessage>>() {
                @Override
                public void onResponse(Call<ArrayList<ChatMessage>> call, Response<ArrayList<ChatMessage>> response) {
                    ArrayList<ChatMessage> chatMessages = response.body();
                    if (chatMessages != null)
                        chatListener.onSuccess(chatMessages);
                    else chatListener.onFailure();
                    Log.i("tag","getChatMessages ");
                }

                @Override
                public void onFailure(Call<ArrayList<ChatMessage>> call, Throwable t) {
                    Log.i("tag","getChatMessages "+t.toString());
                    chatListener.onFailure();
                }
            });
        } catch (Exception e) {
            Log.e("Exception ", "ContactService getChatMessages" + e.toString());
        }
    }

    public void getAllUsers() {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        String classId=SharedPreferencesUtils.loadSelf(context).getClassId();
        Call<ArrayList<User>> call = iContactService.getAllUsers(token,classId);
        try {
            call.enqueue(new Callback<ArrayList<User>>() {
                @Override
                public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                    ArrayList<User> users = response.body();
                    if (users != null)
                        userListener.onSuccess(users);
                    else userListener.onFailure();
                    Log.i("tag","getChatMessages ");
                }

                @Override
                public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                    Log.i("tag","getChatMessages "+t.toString());
                    userListener.onFailure();
                }
            });
        } catch (Exception e) {
            Log.e("Exception ", "ContactService getChatMessages" + e.toString());
        }
    }
}
