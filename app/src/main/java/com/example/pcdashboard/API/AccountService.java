package com.example.pcdashboard.API;

import com.example.pcdashboard.Model.BooleanResponse;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountService {
    @GET("auth/signin")
    Call<Token> getToken();

    @FormUrlEncoded
    @PUT("user/forget-password/{email}")
    Call<BooleanResponse> getPassword(@Path("email") String email);

    @FormUrlEncoded
    @PUT("user/change-password/{id}")
    Call<BooleanResponse> changePassword(@Path("id") String id, @Field("oldPassword") String oldPassword, @Field("newPassword") String newPassword);

    @FormUrlEncoded
    @PUT("user/update-info/{id}")
    Call<BooleanResponse> updateInfo(@Path("id") String id, @Field("email") String email, @Field("phone") String phone);

    //User
    @GET("user/{localId}")
    Call<ArrayList<User>> getAllUsers(@Path("localId") String localId);
}
