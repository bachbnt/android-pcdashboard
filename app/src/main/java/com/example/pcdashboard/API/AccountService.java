package com.example.pcdashboard.API;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Utility.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountService {
    private static AccountService accountService;
    private static IAccountService iAccountService;
    private Context context;
    private AccountListener listener;

    interface AccountListener {
        void onSuccess();

        void onFailure();
    }

    private AccountService(Context context, AccountListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public static AccountService getInstance(Context context, AccountListener listener) {
        if (accountService == null) {
            accountService = new AccountService(context, listener);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WebService.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            iAccountService = retrofit.create(IAccountService.class);
        }
        return accountService;
    }

    public void getToken() {
        Call<Token> call = iAccountService.getToken();
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token=response.body();
                SharedPreferences.saveToken(context,token);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }

    public void forgetPassword(String id) {
        Call<String>call=iAccountService.forgetPassword(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String email=response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void changePassword(String id, String oldPassword, String newPassword) {
      Call<Boolean>call=iAccountService.changePassword(id,oldPassword,newPassword);
      call.enqueue(new Callback<Boolean>() {
          @Override
          public void onResponse(Call<Boolean> call, Response<Boolean> response) {
              listener.onSuccess();;
          }

          @Override
          public void onFailure(Call<Boolean> call, Throwable t) {
              listener.onFailure();
          }
      });
    }

    public void updateInfo(String id, String email, String phone) {
        Call<Boolean>call=iAccountService.updateInfo(id,email,phone);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    public void getSelf(String id) {
        Call<User> call = iAccountService.getSelf(id);
        call.enqueue();
    }
}
