package com.example.pcdashboard.Services;

import android.content.Context;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.example.pcdashboard.Manager.DatabaseHelper;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
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
    private DatabaseHelper databaseHelper;
    private Context context;
    private ClassListener classListener;
    private DepartmentListener departmentListener;
    private CommentListener commentListener;
    private PostListener postListener;
    private EditPostListener editPostListener;
    private EditCommentListener editCommentListener;


    public interface ClassListener {
        void onGetSuccess(ArrayList<ClassPost> classPosts);

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

    public interface EditPostListener {
        void onSuccess();

        void onFailure();
    }

    public interface EditCommentListener {
        void onSuccess();

        void onFailure();
    }

    private PostService(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWebService.urlServer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iPostService = retrofit.create(IPostService.class);
        databaseHelper = DatabaseHelper.getInstance(context);
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

    public void setEditPostListener(EditPostListener editPostListener) {
        this.editPostListener = editPostListener;
    }

    public void setEditCommentListener(EditCommentListener editCommetListener) {
        this.editCommentListener = editCommetListener;
    }

    public static PostService getInstance(Context context) {
        if (postService == null)
            postService = new PostService(context);
        return postService;
    }

    public void getDepartmentPosts(int number) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<ArrayList<DepartmentPost>> call = iPostService.getAllDepartmentPosts(token, number);
        try {
            call.enqueue(new Callback<ArrayList<DepartmentPost>>() {
                @Override
                public void onResponse(Call<ArrayList<DepartmentPost>> call, Response<ArrayList<DepartmentPost>> response) {
                    ArrayList<DepartmentPost> departmentPosts = response.body();
                    if (departmentPosts != null) {
                        databaseHelper.deleteDepartmentPosts();
                        if (departmentPosts.size() < 10) {
                            for (int i = 0; i < departmentPosts.size(); i++)
                                databaseHelper.insertDepartmentPost(departmentPosts.get(i));
                        } else {
                            for (int i = departmentPosts.size() - 10; i < departmentPosts.size(); i++)
                                databaseHelper.insertDepartmentPost(departmentPosts.get(i));
                        }
                        departmentListener.onSuccess(departmentPosts);
                    } else departmentListener.onFailure();
                }

                @Override
                public void onFailure(Call<ArrayList<DepartmentPost>> call, Throwable t) {
                    departmentListener.onFailure();
                }
            });
        } catch (Exception e) {
            Log.e("Exception ", "Post Service getDepartmentPosts" + e.toString());
        }
    }

    public void getClassPosts(int number) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        String classId = SharedPreferencesUtils.loadSelf(context).getClassId();
        Call<ArrayList<ClassPost>> call = iPostService.getAllClassPosts(token, classId, number);
        try {
            call.enqueue(new Callback<ArrayList<ClassPost>>() {
                @Override
                public void onResponse(Call<ArrayList<ClassPost>> call, Response<ArrayList<ClassPost>> response) {
                    ArrayList<ClassPost> classPosts = response.body();
                    if (classPosts != null)
                        classListener.onGetSuccess(classPosts);
                    else classListener.onFailure();
                }

                @Override
                public void onFailure(Call<ArrayList<ClassPost>> call, Throwable t) {
                    classListener.onFailure();
                }
            });
        } catch (Exception e) {
            Log.e("Exception ", "Post Service getClassPosts" + e.toString());
        }
    }

    public void getPostComments(String postId) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<ArrayList<PostComment>> call = iPostService.getAllComments(token, postId);
        try {
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
            Log.e("Exception ", "Post Service getPostComments" + e.toString());
        }
    }

    public void createClassPost(String content, String image) {
        MultipartBody.Part part = null;
        if (image != null) {
            File file = new File(image);
            // Create a request body with file and image media type
            RequestBody fileReqBody = RequestBody.create(MediaType.parse(getMimeType(image)), file);
            // Create MultipartBody.Part using file request-body,file name and part name
            part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
        }
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        try {
            if (part == null) {
                Call<Boolean> call = iPostService.createPost(token, content);
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.body())
                            postListener.onSuccess();
                        else postListener.onFailure();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        postListener.onFailure();
                    }
                });
            } else {
                Call<Boolean> call = iPostService.createPostImg(token, content, part);
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.body())
                            postListener.onSuccess();
                        else postListener.onFailure();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        postListener.onFailure();
                    }
                });
            }
        } catch (Exception e) {
            Log.e("Exception ", "Post Service createClassPost" + e.toString());
        }
    }

    private String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public void updateClassPost(final String postId, String content) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        String image = SharedPreferencesUtils.loadClassPost(context).getImage();
        Call<Boolean> call = iPostService.updatePost(token, postId, content, image);
        try {
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.body())
                        editPostListener.onSuccess();
                    else editPostListener.onFailure();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    editPostListener.onFailure();
                }
            });
        } catch (Exception e) {
            Log.e("Exception ", "Post Service updateClassPost" + e.toString());
        }
    }

    public void deleteClassPost(String postId) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.deletePost(token, postId);
        try {
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
        } catch (Exception e) {
            Log.e("Exception ", "Post Service deleteClassPost" + e.toString());
        }
    }

    public void createPostComment(String content) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        String postId = SharedPreferencesUtils.loadClassPost(context).getId();
        Call<Boolean> call = iPostService.createComment(token, postId, content);
        try {
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
        } catch (Exception e) {
            Log.e("Exception ", "Post Service createPostComment" + e.toString());
        }
    }

    public void updatePostComment(String commentId, String content) {
        Log.i("tag", "comment commentId " + commentId);
        Log.i("tag", "comment content " + commentId);

        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.updateComment(token, commentId, content);
        try {
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.body())
                        editCommentListener.onSuccess();
                    else editCommentListener.onFailure();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Log.i("tag", "updatePostComment " + t.toString());
                    editCommentListener.onFailure();
                }
            });
        } catch (Exception e) {
            Log.e("Exception ", "Post Service updatePostComment" + e.toString());
        }
    }

    public void deletePostComment(String commentId) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.deleteComment(token, commentId);
        try {
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
        } catch (Exception e) {
            Log.e("Exception ", "Post Service deletePostComment" + e.toString());
        }
    }
}
