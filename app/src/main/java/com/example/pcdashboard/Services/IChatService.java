package com.example.pcdashboard.Services;

import com.example.pcdashboard.Model.ChatMessage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IChatService {
    //Chat
    @GET("message/")
    Call<ArrayList<ChatMessage>> getChatMessages(@Header("Authorization") String token);
}
