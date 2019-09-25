package com.example.pcdashboard.API;

import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IAccountService {
    @GET("auth/signin")
    Call<Token> getToken();

    @FormUrlEncoded
    @PUT("user/forget-password/{id}")
    Call<String> forgetPassword(@Path("id") String id);

    @FormUrlEncoded
    @PUT("user/change-password/{id}")
    Call<Boolean> changePassword(@Path("id") String id, @Field("oldPassword") String oldPassword, @Field("newPassword") String newPassword);

    @FormUrlEncoded
    @PUT("user/update-info/{id}")
    Call<Boolean> updateInfo(@Path("id") String id, @Field("email") String email, @Field("phone") String phone);

    //User
    @GET("user/id")
    Call<User> getSelf(@Path("id") String id);

    @GET("user/all/{id}")
    Call<ArrayList<User>> getAllUsers(@Path("id") String id);
}
