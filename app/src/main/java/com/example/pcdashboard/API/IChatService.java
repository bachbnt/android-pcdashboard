package com.example.pcdashboard.API;

import com.example.pcdashboard.Model.ChatMessage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IChatService {
    //Chat
    @GET("chat/{classId}")
    Call<ArrayList<ChatMessage>> getChatMessages(@Path("classId") String classId, @Field("quantity") int quantity);
}
