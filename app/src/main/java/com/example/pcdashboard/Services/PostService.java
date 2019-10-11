package com.example.pcdashboard.Services;

import android.content.Context;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.example.pcdashboard.Manager.SharedPreferencesUtil;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Model.PostComment;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    private PostListener postListener;
    private EditListener editListener;


    public interface ClassListener {
        void onGetSuccess(ArrayList<ClassPost> classPosts);

        void onEditSuccess();

        void onDeleteSuccess();

        void onFailure();
    }

    public interface DepartmentListener {
        void onSuccess(ArrayList<DepartmentPost> departmentPosts);

        void onFailure();
    }

    public interface CommentListener {
        void onGetSuccess(ArrayList<PostComment> postComments);

        void onSuccess();

        void onFailure();
    }

    public interface PostListener {
        void onSuccess();

        void onFailure();
    }

    public interface EditListener {
        void onSuccess();

        void onFailure();
    }

    private PostService(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWebServices.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iPostService = retrofit.create(IPostService.class);
    }

    public void setClassListener(ClassListener classListener) {
        this.classListener = classListener;
    }

    public void setDepartmentListener(DepartmentListener departmentListener) {
        this.departmentListener = departmentListener;
    }

    public void setCommentListener(CommentListener commentListener) {
        this.commentListener = commentListener;
    }

    public void setPostListener(PostListener postListener) {
        this.postListener = postListener;
    }

    public void setEditListener(EditListener editListener) {
        this.editListener = editListener;
    }


    public static PostService getInstance(Context context) {
        if (postService == null)
            postService = new PostService(context);
        return postService;
    }

    public void getDepartmentPosts() {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();

        Call<ArrayList<DepartmentPost>> call = iPostService.getAllDepartmentPosts(token);
        call.enqueue(new Callback<ArrayList<DepartmentPost>>() {
            @Override
            public void onResponse(Call<ArrayList<DepartmentPost>> call, Response<ArrayList<DepartmentPost>> response) {
                ArrayList<DepartmentPost> departmentPosts = response.body();
                try {
                    if (departmentPosts != null)
                        departmentListener.onSuccess(departmentPosts);
                    else departmentListener.onFailure();
                } catch (NullPointerException e) {
                    Log.e("tag", "NullPointerException getDepartmentPosts " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DepartmentPost>> call, Throwable t) {
                departmentListener.onFailure();
            }
        });
    }

    public void getClassPosts() {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        String classId = SharedPreferencesUtil.loadSelf(context).getClassId();
        Call<ArrayList<ClassPost>> call = iPostService.getAllClassPosts(token, classId);
        call.enqueue(new Callback<ArrayList<ClassPost>>() {
            @Override
            public void onResponse(Call<ArrayList<ClassPost>> call, Response<ArrayList<ClassPost>> response) {
                ArrayList<ClassPost> classPosts = response.body();
                try {
                    if (classPosts != null)
                        classListener.onGetSuccess(classPosts);
                    else classListener.onFailure();
                } catch (NullPointerException e) {
                    Log.e("tag", "NullPointerException getClassPosts " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ClassPost>> call, Throwable t) {
                classListener.onFailure();
            }
        });
    }

    public void getPostComments(String postId) {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        try {
            Call<ArrayList<PostComment>> call = iPostService.getAllComments(token, postId);
            call.enqueue(new Callback<ArrayList<PostComment>>() {
                @Override
                public void onResponse(Call<ArrayList<PostComment>> call, Response<ArrayList<PostComment>> response) {
                    ArrayList<PostComment> postComments = response.body();
                    if (postComments != null)
                        commentListener.onGetSuccess(postComments);
                    else commentListener.onFailure();
                }

                @Override
                public void onFailure(Call<ArrayList<PostComment>> call, Throwable t) {
                    commentListener.onFailure();
                }
            });
        } catch (NullPointerException e) {
            Log.e("tag", "NullPointerException getPostComments " + e.toString());
        }
    }

    public void createClassPost(String content, String image) {
        File file = new File(image);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse(getMimeType(image)), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
        Log.i("tag", "createClassPost " + part.body().toString());
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.createPost(token, content, part);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body())
                    postListener.onSuccess();
                else postListener.onFailure();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("tag", "createClassPost " + t.toString());
                postListener.onFailure();
            }
        });
    }

    public String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public void updateClassPost(final String postId, String content, String image) {
        File file = new File(image);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse(getMimeType(image)), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.updatePost(token, postId, content, part);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body())
                    classListener.onEditSuccess();
                else classListener.onFailure();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                classListener.onFailure();
            }
        });
    }

    public void deleteClassPost(String postId) {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.deletePost(token, postId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body())
                    classListener.onDeleteSuccess();
                else classListener.onFailure();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                classListener.onFailure();
            }
        });
    }

    public void createPostComment(String content) {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        String postId = SharedPreferencesUtil.loadPost(context);
        Call<Boolean> call = iPostService.createComment(token, postId, content);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body())
                    commentListener.onSuccess();
                else commentListener.onFailure();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                commentListener.onFailure();
            }
        });
    }

    public void updatePostComment(String commentId, String content) {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.updateComment(token, commentId, content);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body())
                    commentListener.onSuccess();
                else commentListener.onSuccess();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                commentListener.onSuccess();
            }
        });
    }

    public void deletePostComment(String commentId) {
        String token = SharedPreferencesUtil.loadToken(context).getTokenType() + " " + SharedPreferencesUtil.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.deleteComment(token, commentId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body())
                    commentListener.onSuccess();
                else commentListener.onSuccess();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                commentListener.onFailure();
            }
        });
    }
}
