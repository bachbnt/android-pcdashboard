package com.example.pcdashboard.Services;

import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Request.InfoRequest;
import com.example.pcdashboard.Request.PasswordRequest;
import com.example.pcdashboard.Request.TokenRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IAccountService {
    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @POST("auth/signin")
    Call<Token> getToken(@Body TokenRequest tokenRequest);

    @PUT("user/forget-password/{userId}")
    Call<String> forgetPassword(@Path("userId") String id);


    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @PUT("user/change-password/")
    Call<Boolean> changePassword(@Header("Authorization") String token, @Body PasswordRequest passwordRequest);


    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @PUT("user/")
    Call<User> updateInfo(@Header("Authorization") String token, @Body InfoRequest infoRequest);

    @GET("user/")
    Call<User> getSelf(@Header("Authorization") String token);

//    @GET("user/all/{userId}")
//    Call<ArrayList<User>> getAllUsers(@Path("userId") String id);
}
