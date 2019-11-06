package com.example.pcdashboard.Services;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IWebService {
        String urlServer = "http://pcdashboard.herokuapp.com/";
        String urlHome="http://www.phys.hcmus.edu.vn/vlth/";

        @PUT("user/fcm")
        Call<Boolean> sendFCMToken(@Header("Authorization") String token, @Query("fcmToken") String fcmToken);

}
