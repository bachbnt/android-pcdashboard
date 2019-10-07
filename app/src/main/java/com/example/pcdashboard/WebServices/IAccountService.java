package com.example.pcdashboard.WebServices;

import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.ObjectsRequest.InfoRequest;
import com.example.pcdashboard.ObjectsRequest.PasswordRequest;
import com.example.pcdashboard.ObjectsRequest.TokenRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
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
    Call<Boolean> updateInfo(@Header("Authorization") String token, @Body InfoRequest infoRequest);

    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @GET("user/{userId}")
    Call<User> getSelf(@Header("Authorization") String token, @Path("userId") String id);

//    @GET("user/all/{userId}")
//    Call<ArrayList<User>> getAllUsers(@Path("userId") String id);
}
