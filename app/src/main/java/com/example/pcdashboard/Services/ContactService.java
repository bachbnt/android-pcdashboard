package com.example.pcdashboard.Services;

import android.content.Context;

import com.example.pcdashboard.Manager.DatabaseHelper;
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
    private DatabaseHelper databaseHelper;
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
        databaseHelper = DatabaseHelper.getInstance(context);
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
        Call<ArrayList<ChatMessage>> call = iContactService.getChatMessages(token, number);
        call.enqueue(new Callback<ArrayList<ChatMessage>>() {
            @Override
            public void onResponse(Call<ArrayList<ChatMessage>> call, Response<ArrayList<ChatMessage>> response) {
                ArrayList<ChatMessage> chatMessages = response.body();
                if (chatListener != null)
                    if (chatMessages != null) {
                        databaseHelper.deleteChatMessages();
                        if (chatMessages.size() < 20) {
                            for (int i = 0; i < chatMessages.size(); i++)
                                databaseHelper.insertChatMessage(chatMessages.get(i));
                        } else {
                            for (int i = chatMessages.size() - 20; i < chatMessages.size(); i++)
                                databaseHelper.insertChatMessage(chatMessages.get(i));
                        }
                        chatListener.onSuccess(chatMessages);
                    } else chatListener.onFailure();
            }

            @Override
            public void onFailure(Call<ArrayList<ChatMessage>> call, Throwable t) {
                if (chatListener != null)
                    chatListener.onFailure();
            }
        });
    }

    public void getUsers(final String classId) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<ArrayList<User>> call = iContactService.getAllUsers(token, classId);
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> users = response.body();
                if (userListener != null)
                    if (users != null) {
                        if (classId.equals("GV")) {
                            databaseHelper.deleteUserTeachers();
                            for (User user : users)
                                databaseHelper.insertUserTeacher(user);
                        } else if( SharedPreferencesUtils.loadStudentYear(context)==3){
                            databaseHelper.deleteYearStudents(3);
                            for (User user : users)
                                databaseHelper.insertYearStudent(user,3);
                        }
                        else  if(SharedPreferencesUtils.loadStudentYear(context)==4){
                            databaseHelper.deleteYearStudents(4);
                            for (User user : users)
                                databaseHelper.insertYearStudent(user,4);
                        }
                        else {
                            databaseHelper.deleteUserStudents();
                            for (User user : users)
                                databaseHelper.insertUserStudent(user);
                        }
                        userListener.onSuccess(users);
                    } else userListener.onFailure();
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                if (userListener != null)
                    userListener.onFailure();
            }
        });

    }
}
