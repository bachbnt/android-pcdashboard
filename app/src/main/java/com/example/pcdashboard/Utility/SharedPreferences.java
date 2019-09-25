package com.example.pcdashboard.Utility;

import android.content.Context;

import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;

public class SharedPreferences {
    public static void saveToken(Context context, Token token) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("accessToken", token.getAccessToken());
        editor.putString("tokenType", token.getTokenType());
        editor.commit();
    }

    public static Token loadToken(Context context) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        String accessToken= preferences.getString("accessToken",null);
        String tokenType=preferences.getString("tokenType",null);
        Token token=new Token(accessToken,tokenType);
        return token;
    }

    public static void saveSelf(Context context, User self) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("self", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id", self.getId());
        editor.putString("name",self.getName());
        editor.putString("email",self.getEmail());
        editor.putString("phone",self.getPhone());
        editor.putString("avatar",self.getAvatar());
        editor.commit();
    }

    public static User loadSelf(Context context) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("self", Context.MODE_PRIVATE);
        String id= preferences.getString("id",null);
        String name=preferences.getString("name",null);
        String email=preferences.getString("email",null);
        String phone=preferences.getString("phone",null);
        String avatar=preferences.getString("avatar",null);
        int status=1;
        User self=new User(id,name,email,phone,avatar,status);
        return self;
    }

    public static String loadIdPreferences(Context context, String key){
        android.content.SharedPreferences preferences=context.getSharedPreferences("caches",Context.MODE_PRIVATE);
        return preferences.getString(key,null);
    }

    public static String loadEmailPreferences(Context context, String key){
        android.content.SharedPreferences preferences=context.getSharedPreferences("caches",Context.MODE_PRIVATE);
        return preferences.getString(key,null);
    }

    public static void removePreferences(Context context, String key) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("caches", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void clearPreferences(Context context, String key) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("caches", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
