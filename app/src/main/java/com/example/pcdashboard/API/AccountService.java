package com.example.pcdashboard.API;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.Model.BooleanResponse;
import com.example.pcdashboard.Model.Token;
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

    private AccountService(Context context) {
        this.context=context;
    }
    public static AccountService getInstance(Context context){
        if(accountService==null){
            accountService=new AccountService(context);
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(WebService.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            iAccountService=retrofit.create(IAccountService.class);
        }
        return accountService;
    }

    public void getToken(){
        Call<Token> call=iAccountService.getToken();
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

    public void forgetPassword(String id){
        Call<BooleanResponse> call=iAccountService.forgetPassword(id);
        call.enqueue(new Callback<BooleanResponse>() {
            @Override
            public void onResponse(Call<BooleanResponse> call, Response<BooleanResponse> response) {
                BooleanResponse booleanResponse=response.body();
                Log.i("tag","forgetPassword onResponse "+booleanResponse.success);
            }

            @Override
            public void onFailure(Call<BooleanResponse> call, Throwable t) {
                Log.i("tag","forgetPassword onFailure "+t.toString());
            }
        });
    }
    public void changePassword(String id,String oldPassword,String newPassword){
        Call<BooleanResponse> call=iAccountService.changePassword(id,oldPassword,newPassword);
        call.enqueue(new Callback<BooleanResponse>() {
            @Override
            public void onResponse(Call<BooleanResponse> call, Response<BooleanResponse> response) {
                BooleanResponse booleanResponse=response.body();
                Log.i("tag","changePassword onResponse "+booleanResponse.success);
            }

            @Override
            public void onFailure(Call<BooleanResponse> call, Throwable t) {
                Log.i("tag","changePassword onFailure "+t.toString());
            }
        });
    }
    public void updateInfo(String id,String email,String phone){
        Call<BooleanResponse>call=iAccountService.updateInfo(id,email,phone);
        call.enqueue(new Callback<BooleanResponse>() {
            @Override
            public void onResponse(Call<BooleanResponse> call, Response<BooleanResponse> response) {
                BooleanResponse booleanResponse=response.body();
                Log.i("tag","updateInfo onResponse "+booleanResponse.success);
            }

            @Override
            public void onFailure(Call<BooleanResponse> call, Throwable t) {
                Log.i("tag","updateInfo onFailure "+t.toString());
            }
        });
    }
}
