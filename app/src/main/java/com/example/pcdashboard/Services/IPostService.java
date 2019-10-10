package com.example.pcdashboard.Services;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Model.PostComment;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPostService {
    //DEPARTMENT POST
    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @GET("post/department")
    Call<ArrayList<DepartmentPost>> getAllDepartmentPosts(@Header("Authorization") String token);

    //CLASS POST
    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @GET("post/class/{classId}")
    Call<ArrayList<ClassPost>> getAllClassPosts(@Header("Authorization") String token, @Path("classId") String classId);

    @Multipart
    @POST("post/class/")
    Call<Boolean> createPost(@Header("Authorization") String token, @Part MultipartBody.Part file, @Query("content") String content);

//    @Headers(
//            {
//                    "Content-Type:application/json",
//            }
//    )
//    @PUT("post/class/{postId}")
//    Call<Boolean> updatePost(@Header("Authorization") String token, @Path("postId") String postId, @Body PostRequest postRequest);

    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @DELETE("post/class/{postId}")
    Call<Boolean> deletePost(@Header("Authorization") String token, @Path("postId") String postId);

    //POST COMMENT

    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @GET("comment/{postId}")
    Call<ArrayList<PostComment>> getAllComments(@Header("Authorization") String token, @Path("postId") String postId);

    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @POST("comment/{postId}")
    Call<Boolean> createComment(@Header("Authorization") String token, @Path("postId") String postId, @Body String  content);

    @Headers(
            {
                    "Content-Type:application/json",
            }
    )
    @DELETE("comment/{commentId}")
    Call<Boolean> deleteComment(@Header("Authorization") String token, @Path("commentId") String commentId);

}
