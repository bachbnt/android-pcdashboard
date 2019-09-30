package com.example.pcdashboard.API;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Utility.SharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostService {
    private static PostService postService;
    private static IPostService iPostService;
    private Context context;
    private PostListener listener;
    public interface PostListener{
        void onDepartmentSuccess(ArrayList<DepartmentPost> departmentPosts);
        void onClassSuccess(ArrayList<ClassPost> classPosts);
        void onFailure();
    }

    public PostService(Context context) {
        this.context = context;

    }

    public void setListener(PostListener listener) {
        this.listener = listener;
    }

    public static PostService getInstance(Context context){
        if(postService==null)
            postService=new PostService(context);
        return postService;
    }
    public void getDepartmentPosts(){
        String token = SharedPreferences.loadToken(context).getTokenType() + " " + SharedPreferences.loadToken(context).getAccessToken();
        Call<ArrayList<DepartmentPost>> call=iPostService.getAllDepartmentPosts(token);
        call.enqueue(new Callback<ArrayList<DepartmentPost>>() {
            @Override
            public void onResponse(Call<ArrayList<DepartmentPost>> call, Response<ArrayList<DepartmentPost>> response) {
                ArrayList<DepartmentPost> departmentPosts=response.body();
                listener.onDepartmentSuccess(departmentPosts);
            }

            @Override
            public void onFailure(Call<ArrayList<DepartmentPost>> call, Throwable t) {
                Log.e("tag","getDepartmentPosts onFailure"+t.toString());
                listener.onFailure();
            }
        });
    }
    public void getClassPosts(){
        String token = SharedPreferences.loadToken(context).getTokenType() + " " + SharedPreferences.loadToken(context).getAccessToken();
        String classId=SharedPreferences.loadSelf(context).getClassId();
        Call<ArrayList<ClassPost>> call=iPostService.getAllClassPosts(token,classId);
        call.enqueue(new Callback<ArrayList<ClassPost>>() {
            @Override
            public void onResponse(Call<ArrayList<ClassPost>> call, Response<ArrayList<ClassPost>> response) {
                ArrayList<ClassPost> classPosts=response.body();
                listener.onClassSuccess(classPosts);
            }

            @Override
            public void onFailure(Call<ArrayList<ClassPost>> call, Throwable t) {
                Log.e("tag","getClassPosts onFailure"+t.toString());
                listener.onFailure();
            }
        });
    }
}
