package com.example.pcdashboard.Manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;

public class SharedPreferencesUtil {
    public static void saveToken(Context context, Token token) {
        SharedPreferences preferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("accessToken", token.getAccessToken());
        editor.putString("tokenType",token.getTokenType());
        editor.putString("userId",token.getUserId());
        editor.commit();
    }

    public static Token loadToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        String accessToken=preferences.getString("accessToken",null);
        String tokenType=preferences.getString("tokenType",null);
        String userId=preferences.getString("userId",null);
        Token token=new Token(accessToken,tokenType,userId);
        return token;
    }

    public static void clearToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void saveStatus(Context context,boolean status){
        SharedPreferences preferences=context.getSharedPreferences("status",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("status",status);
        editor.commit();
    }

    public static boolean loadStatus(Context context){
        SharedPreferences preferences=context.getSharedPreferences("status",Context.MODE_PRIVATE);
        boolean status=preferences.getBoolean("status",false);
        return status;
    }

    public static void saveSelf(Context context, User self) {
        SharedPreferences preferences = context.getSharedPreferences("self", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id", self.getId());
        editor.putString("name",self.getName());
        editor.putString("email",self.getEmail());
        editor.putString("phone",self.getPhone());
        editor.putString("avatar",self.getAvatar());
        editor.putString("classId",self.getClassId());
        editor.putString("role",self.getRole());
        editor.commit();
    }

    public static User loadSelf(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("self", Context.MODE_PRIVATE);
        String id= preferences.getString("id",null);
        String name=preferences.getString("name",null);
        String email=preferences.getString("email",null);
        String phone=preferences.getString("phone",null);
        String avatar=preferences.getString("avatar",null);
        String classId=preferences.getString("classId",null);
        String role=preferences.getString("role",null);
        User self=new User(id,name,email,phone,avatar,classId,role);
        return self;
    }

    public static void clearSelf(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("self", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void saveEmail(Context context,String email){
        SharedPreferences preferences=context.getSharedPreferences("email",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("email",email);
        editor.commit();
    }

    public static String loadEmail(Context context){
        SharedPreferences preferences=context.getSharedPreferences("email",Context.MODE_PRIVATE);
        String email=preferences.getString("email",null);
        return email;
    }

    public static void clearEmail(Context context) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("email", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void savePost(Context context, ClassPost classPost){
        SharedPreferences preferences=context.getSharedPreferences("post",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("postId",classPost.getId());
        editor.commit();
    }

    public static String loadPost(Context context){
        SharedPreferences preferences=context.getSharedPreferences("post",Context.MODE_PRIVATE);
        String postId=preferences.getString("postId",null);
        return postId;
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
}
