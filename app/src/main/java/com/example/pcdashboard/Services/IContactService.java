package com.example.pcdashboard.Services;

import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IContactService {
    //Chat
    @GET("message/")
    Call<ArrayList<ChatMessage>> getChatMessages(@Header("Authorization") String token, @Query("number")int number);

    @GET("user/{classId}")
    Call<ArrayList<User>> getAllUsers(@Header("Authorization") String token,@Path("classId")String classId);
}
