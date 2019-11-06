package com.example.pcdashboard.Manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Model.Token;
import com.example.pcdashboard.Model.User;

public class SharedPreferencesUtils {
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

    public static void saveFCMToken(Context context, String fcmToken) {
        SharedPreferences preferences = context.getSharedPreferences("fcm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fcmToken", fcmToken);
        editor.commit();
    }

    public static String loadFCMToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("fcm", Context.MODE_PRIVATE);
        String fcmToken=preferences.getString("fcmToken","");
        return fcmToken;
    }

    public static void clearToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void saveStatusLogin(Context context, boolean status){
        SharedPreferences preferences=context.getSharedPreferences("status",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("status",status);
        editor.commit();
    }

    public static boolean loadStatusLogin(Context context){
        SharedPreferences preferences=context.getSharedPreferences("status",Context.MODE_PRIVATE);
        boolean status=preferences.getBoolean("status",false);
        return status;
    }

    public static void saveFirstRequestSchedule(Context context, boolean first){
        SharedPreferences preferences=context.getSharedPreferences("first",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("first",first);
        editor.commit();
    }

    public static boolean loadFirstRequestSchedule(Context context){
        SharedPreferences preferences=context.getSharedPreferences("first",Context.MODE_PRIVATE);
        boolean first=preferences.getBoolean("first",true);
        return first;
    }

    public static void saveTabId(Context context,int tabId){
        SharedPreferences preferences=context.getSharedPreferences("tab",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("tab",tabId);
        editor.commit();
    }

    public static int loadTabId(Context context){
        SharedPreferences preferences=context.getSharedPreferences("tab",Context.MODE_PRIVATE);
        int tabId=preferences.getInt("tab",0);
        return tabId;
    }

    public static void clearTabId(Context context) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("tab", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
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

    public static void saveEmailForgot(Context context, String email){
        SharedPreferences preferences=context.getSharedPreferences("email",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("email",email);
        editor.commit();
    }

    public static String loadEmailForgot(Context context){
        SharedPreferences preferences=context.getSharedPreferences("email",Context.MODE_PRIVATE);
        String email=preferences.getString("email",null);
        return email;
    }

    public static void clearEmailForgot(Context context) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("email", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void saveClassPost(Context context, ClassPost classPost){
        SharedPreferences preferences=context.getSharedPreferences("post",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("postId",classPost.getId());
        editor.putString("content",classPost.getContent());
        editor.putString("image",classPost.getImage());
        editor.commit();
    }

    public static ClassPost loadClassPost(Context context){
        SharedPreferences preferences=context.getSharedPreferences("post",Context.MODE_PRIVATE);
        String postId=preferences.getString("postId",null);
        String content=preferences.getString("content",null);
        String image=preferences.getString("image",null);
        ClassPost classPost=new ClassPost(postId,null,content,image,null,null,null);
        return classPost;
    }

    public static void clearClassPost(Context context) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("post", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void savePostComment(Context context, PostComment postComment){
        SharedPreferences preferences=context.getSharedPreferences("comment",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("commentId",postComment.getId());
        editor.putString("content",postComment.getContent());
        editor.commit();
    }

    public static PostComment loadPostComment(Context context){
        SharedPreferences preferences=context.getSharedPreferences("comment",Context.MODE_PRIVATE);
        String commentId=preferences.getString("commentId",null);
        String content=preferences.getString("content",null);
        PostComment postComment=new PostComment(commentId,  content,null,null,null,null);
        return postComment;
    }

    public static void clearPostComment(Context context) {
        android.content.SharedPreferences preferences = context.getSharedPreferences("comment", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
