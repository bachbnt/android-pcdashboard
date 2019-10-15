package com.example.pcdashboard.Services;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.Request.InfoRequest;
import com.example.pcdashboard.Request.PasswordRequest;
import com.example.pcdashboard.Request.TokenRequest;
import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountService {
    private static AccountService accountService;
    private static IAccountService iAccountService;
    private Context context;
    private LoginListener loginListener;
    private ForgotListener forgotListener;
    private InfoListener infoListener;
    private PasswordListener passwordListener;

    public interface LoginListener {
        void onTokenSuccess(Token token);

        void onSelfSuccess(User self);

        void onLoginFailure();

    }

    public interface ForgotListener {
        void onSuccess();

        void onFailure();
    }

    public interface InfoListener {
        void onSuccess(User self);

        void onFailure();
    }

    public interface PasswordListener {
        void onSuccess();

        void onFailure();
    }

    private AccountService(Context context) {
        this.context = context;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWebServices.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        iAccountService = retrofit.create(IAccountService.class);
    }

    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    public void setForgotListener(ForgotListener listener) {
        this.forgotListener = listener;
    }

    public void setInfoListener(InfoListener listener) {
        this.infoListener = listener;
    }

    public void setPasswordListener(PasswordListener listener) {
        this.passwordListener = listener;
    }

    public static AccountService getInstance(Context context) {
        if (accountService == null)
            accountService = new AccountService(context);
        return accountService;
    }

    public void getToken(String userId, String password) {
        Call<Token> call = iAccountService.getToken(new TokenRequest(userId, password));
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token = response.body();
                if (token != null)
                    loginListener.onTokenSuccess(token);
                else loginListener.onLoginFailure();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.i("tag","getToken onFailure "+t.toString());
                loginListener.onLoginFailure();
            }
        });
    }

    public void forgotPassword(String userId) {
        Call<String> call = iAccountService.forgetPassword(userId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String email = response.body();
                if (email != null) {
                    SharedPreferencesUtil.saveEmailForgot(context, email);
                    forgotListener.onSuccess();
                } else forgotListener.onFailure();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                forgotListener.onFailure();
            }
        });
    }

    public void changePassword(String oldPassword, String newPassword) {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<Boolean> call = iAccountService.changePassword(token, new PasswordRequest(SharedPreferencesUtil.loadSelf(context).getId(), oldPassword, newPassword));
        try {
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.body())
                        passwordListener.onSuccess();
                    else passwordListener.onFailure();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Log.i("tag", "changePassword onFailure " + t.toString());
                    passwordListener.onFailure();
                }
            });
        }catch (NullPointerException e){
            Log.i("tag", "changePassword  NullPointerException " + e.toString());
        }

    }

    public void updateInfo(String email, String phone) {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<User> call = iAccountService.updateInfo(token, new InfoRequest(SharedPreferencesUtil.loadSelf(context).getId(), email, phone));
        try {
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User self=response.body();
                    if (self!=null)
                        infoListener.onSuccess(self);
                    else infoListener.onFailure();
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.i("tag","updateInfo onFailure "+t.toString());
                    infoListener.onFailure();
                }
            });
        }catch (NullPointerException e){
            Log.i("tag","updateInfo NullPointerException "+e.toString());
        }

    }

    public void getSelf() {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<User> call = iAccountService.getSelf(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User self = response.body();
                if (self != null)
                    loginListener.onSelfSuccess(self);
                else loginListener.onLoginFailure();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("tag","getSelf onFailure "+t.toString());
                loginListener.onLoginFailure();
            }
        });
    }

//    public void getAllUsers(String userId) {
//        Call<ArrayList<User>> call = iAccountService.getAllUsers(userId);
//        call.enqueue(new Callback<ArrayList<User>>() {
//            @Override
//            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
//                ArrayList<User> users = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
//
//            }
//        });
//    }
}