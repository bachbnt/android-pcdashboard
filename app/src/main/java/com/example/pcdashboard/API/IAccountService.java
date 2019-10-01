package com.example.pcdashboard.API;

import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Request.TokenRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

//    @Headers(
//            {
//                    "Content-Type:application/json",
//                    "Authorization:key=AAAArOvOFOI:APA91bGvSEbjNZBLWyRFlpusgCi438wjcZhonilnyXIW7964K04QQ36iH3RsOkShe3eKg4aVSwQWDjCt1FiJqc7rphh8SSzD7IYRPjmNJK90XRlEkJUQtuvq5UMOyp6eUJ9jV-qb_Qc9"
//            }
//    )
//
//    @FormUrlEncoded
//    @PUT("user/change-password/{userId}")
//    Call<Boolean> changePassword(@Path("userId") String id, @Field("oldPassword") String oldPassword, @Field("newPassword") String newPassword);

//    @Headers(
//            {
//                    "Content-Type:application/json",
//                    "Authorization:key=AAAArOvOFOI:APA91bGvSEbjNZBLWyRFlpusgCi438wjcZhonilnyXIW7964K04QQ36iH3RsOkShe3eKg4aVSwQWDjCt1FiJqc7rphh8SSzD7IYRPjmNJK90XRlEkJUQtuvq5UMOyp6eUJ9jV-qb_Qc9"
//            }
//    )
//    @FormUrlEncoded
//    @PUT("user/update-info/{userId}")
//    Call<Boolean> updateInfo(@Path("userId") String id, @Field("email") String email, @Field("phone") String phone);

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
