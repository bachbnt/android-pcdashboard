package com.example.pcdashboard.API;

import android.content.Context;

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
        void onClassSuccess();
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
                listener.onFailure();
            }
        });
    }
}
