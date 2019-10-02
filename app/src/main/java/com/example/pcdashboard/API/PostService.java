package com.example.pcdashboard.API;

import android.content.Context;
import android.util.Log;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Request.CommentRequest;
import com.example.pcdashboard.Utility.SharedPreferencesUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostService {
    private static PostService postService;
    private static IPostService iPostService;
    private Context context;
    private ClassListener classListener;
    private DepartmentListener departmentListener;
    private CommentListener commentListener;


    public interface ClassListener{
        void onSuccess(ArrayList<ClassPost> classPosts);
        void onFailure();
    }

    public interface DepartmentListener{
        void onSuccess(ArrayList<DepartmentPost> departmentPosts);
        void onFailure();
    }

    public interface CommentListener{
        void onSuccess(ArrayList<PostComment> postComments);
        void onFailure();
    }

    private PostService(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IServiceManager.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iPostService = retrofit.create(IPostService.class);
    }

    public void setClassListener(ClassListener classListener){
        this.classListener=classListener;
    }
    public void setDepartmentListener(DepartmentListener departmentListener){
        this.departmentListener=departmentListener;
    }
    public void setCommentListener(CommentListener commentListener){
        this.commentListener=commentListener;
    }

    public static PostService getInstance(Context context){
        if(postService==null)
            postService=new PostService(context);
        return postService;
    }
    public void getDepartmentPosts(){
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<ArrayList<DepartmentPost>> call=iPostService.getAllDepartmentPosts(token);
        call.enqueue(new Callback<ArrayList<DepartmentPost>>() {
            @Override
            public void onResponse(Call<ArrayList<DepartmentPost>> call, Response<ArrayList<DepartmentPost>> response) {
                ArrayList<DepartmentPost> departmentPosts=response.body();
                departmentListener.onSuccess(departmentPosts);
            }

            @Override
            public void onFailure(Call<ArrayList<DepartmentPost>> call, Throwable t) {
                Log.e("tag","getDepartmentPosts onFailure"+t.toString());
                departmentListener.onFailure();
            }
        });
    }
    public void getClassPosts(){
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        String classId= SharedPreferencesUtil.loadSelf(context).getClassId();
        Call<ArrayList<ClassPost>> call=iPostService.getAllClassPosts(token,classId);
        call.enqueue(new Callback<ArrayList<ClassPost>>() {
            @Override
            public void onResponse(Call<ArrayList<ClassPost>> call, Response<ArrayList<ClassPost>> response) {
                ArrayList<ClassPost> classPosts=response.body();
                Log.i("tag","getClassPosts onResponse");
                classListener.onSuccess(classPosts);
            }

            @Override
            public void onFailure(Call<ArrayList<ClassPost>> call, Throwable t) {
                Log.i("tag","getClassPosts onFailure"+t.toString());
                classListener.onFailure();
            }
        });
    }
    public void getPostComments(String postId){
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<ArrayList<PostComment>> call=iPostService.getAllComments(token,postId);
        call.enqueue(new Callback<ArrayList<PostComment>>() {
            @Override
            public void onResponse(Call<ArrayList<PostComment>> call, Response<ArrayList<PostComment>> response) {
                ArrayList<PostComment> postComments=response.body();
                Log.i("tag","getPostComments"+postComments.size());
                commentListener.onSuccess(postComments);
            }

            @Override
            public void onFailure(Call<ArrayList<PostComment>> call, Throwable t) {
                Log.i("tag","getPostComments"+t.toString());
            }
        });
    }
}
