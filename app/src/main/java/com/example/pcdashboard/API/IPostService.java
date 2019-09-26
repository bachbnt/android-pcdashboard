package com.example.pcdashboard.API;

import com.example.pcdashboard.Model.DepartmentPost;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface IPostService {
    //ClassPost
//    @GET("post/class/{classId}")
//    Call<ArrayList<ClassPost>> getAllClassPosts(@Path("classId") String classId);
//
//    @FormUrlEncoded
//    @POST("post/class/{classId}")
//    Call<ClassPost> createClassPost(@Path("classId") String classId, @Field("userId") String userId, @Field("content") String content, @Field("image") String image);
//
//    @FormUrlEncoded
//    @PUT("post/class/{postId}")
//    Call<ClassPost> updateClassPost(@Path("postId") String postId, @Field("content") String content, @Field("image") String image);

    //    @DELETE("post/class/{postId}")
//    Call<Response> deleteClassPost(@Path("postId") String postId);
//
//    //Comment
//    @GET("comment/{postId}")
//    Call<ArrayList<PostComment>> getAllPostComments(@Path("postId")String postId);
//
//    @FormUrlEncoded
//    @POST("comment/{postId}")
//    Call<PostComment> createPostComment(@Path("postId") String postId, @Field("content")String content,@Field("userId") String userId);
//
//    @DELETE("comment/{commentId}")
//    Call<Response> deletePostComment(@Path("commentId") String commentId);
//
    //DepartmentPost
    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @GET("post/department")
    Call<ArrayList<DepartmentPost>> getAllDepartmentPosts(@Header("Authorization") String token);

}
