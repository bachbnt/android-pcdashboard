package com.example.pcdashboard.Services;

import android.content.Context;

import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Request.TokenRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {
    private static WebService webService;
    private static IWebService iWebService;
    private Context context;

    private WebService(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWebService.urlServer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iWebService = retrofit.create(IWebService.class);
    }
    public static WebService getInstance(Context context){
        if(webService==null)
            webService=new WebService(context);
        return webService;
    }
    public void sendFCMToken(String fcmToken) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<Boolean> call = iWebService.sendFCMToken(token,fcmToken);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
            }
        });
    }
}
