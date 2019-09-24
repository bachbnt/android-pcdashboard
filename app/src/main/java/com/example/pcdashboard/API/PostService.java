package com.example.pcdashboard.API;

import com.example.pcdashboard.Model.BooleanResponse;
import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.Model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostService {
    //ClassPost
    @GET("post/class/{classId}")
    Call<ArrayList<ClassPost>> getAllClassPosts(@Path("classId") String classId);

    @FormUrlEncoded
    @POST("post/class/{classId}")
    Call<ClassPost> createClassPost(@Path("classId") String classId, @Field("userId") String userId, @Field("content") String content, @Field("image") String image);

    @FormUrlEncoded
    @PUT("post/class/{postId}")
    Call<ClassPost> updateClassPost(@Path("postId") String postId, @Field("content") String content, @Field("image") String image);

    @DELETE("post/class/{postId}")
    Call<BooleanResponse> deleteClassPost(@Path("postId") String postId);

    //Comment
    @GET("comment/{postId}")
    Call<ArrayList<PostComment>> getAllPostComments(@Path("postId")String postId);

    @FormUrlEncoded
    @POST("comment/{postId}")
    Call<PostComment> createPostComment(@Path("postId") String postId, @Field("content")String content,@Field("userId") String userId);

    @DELETE("comment/{commentId}")
    Call<BooleanResponse> deletePostComment(@Path("commentId") String commentId);

    //DepartmentPost
    @GET("post/department")
    Call<ArrayList<DepartmentPost>> getAllDepartmentPosts();

}
