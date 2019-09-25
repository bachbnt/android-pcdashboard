package com.example.pcdashboard.Utility;

import android.content.Context;

import com.example.pcdashboard.Model.Token;

public class SharedPreferences {
    public static void saveToken(Context context, Token token) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("caches", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("accessToken", token.getAccessToken());
        editor.putString("tokenType", token.getTokenType());
        editor.commit();
    }

    public static Token loadToken(Context context) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("caches", Context.MODE_PRIVATE);
        String accessToken= preferences.getString("accessToken",null);
        String tokenType=preferences.getString("tokenType",null);
        Token token=new Token(accessToken,tokenType);
        return token;
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
