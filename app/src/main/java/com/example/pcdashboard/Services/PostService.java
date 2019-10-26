package com.example.pcdashboard.Services;

import android.content.Context;
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
        void onSuccess(ArrayList<ClassPost> classPosts);

        void onDeleteSuccess();

        void onFailure();

        void onDeleteFailure();
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
        call.enqueue(new Callback<ArrayList<DepartmentPost>>() {
            @Override
            public void onResponse(Call<ArrayList<DepartmentPost>> call, Response<ArrayList<DepartmentPost>> response) {
                ArrayList<DepartmentPost> departmentPosts = response.body();
                if (departmentListener != null)
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
                if (departmentListener != null)
                    departmentListener.onFailure();
            }
        });

    }

    public void getClassPosts(int number) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        String classId = SharedPreferencesUtils.loadSelf(context).getClassId();
        Call<ArrayList<ClassPost>> call = iPostService.getAllClassPosts(token, classId, number);
        call.enqueue(new Callback<ArrayList<ClassPost>>() {
            @Override
            public void onResponse(Call<ArrayList<ClassPost>> call, Response<ArrayList<ClassPost>> response) {
                ArrayList<ClassPost> classPosts = response.body();
                if (classListener != null)
                    if (classPosts != null) {
                        databaseHelper.deleteClassPosts();
                        if (classPosts.size() < 10) {
                            for (int i = 0; i < classPosts.size(); i++)
                                databaseHelper.insertClassPost(classPosts.get(i));
                        } else {
                            for (int i = classPosts.size() - 10; i < classPosts.size(); i++)
                                databaseHelper.insertClassPost(classPosts.get(i));
                        }
                        classListener.onSuccess(classPosts);
                    } else classListener.onFailure();
            }

            @Override
            public void onFailure(Call<ArrayList<ClassPost>> call, Throwable t) {
                if (classListener != null)
                    classListener.onFailure();
            }
        });
    }

    public void getPostComments(String postId) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<ArrayList<PostComment>> call = iPostService.getAllComments(token, postId);

        call.enqueue(new Callback<ArrayList<PostComment>>() {
            @Override
            public void onResponse(Call<ArrayList<PostComment>> call, Response<ArrayList<PostComment>> response) {
                ArrayList<PostComment> postComments = response.body();
                if (commentListener != null)
                    if (postComments != null)
                        commentListener.onGetSuccess(postComments);
                    else commentListener.onFailure();
            }

            @Override
            public void onFailure(Call<ArrayList<PostComment>> call, Throwable t) {
                if (commentListener != null)
                    commentListener.onFailure();
            }
        });
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
        if (part == null) {
            Call<Boolean> call = iPostService.createPost(token, content);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (postListener != null)
                        if (response.body())
                            postListener.onSuccess();
                        else postListener.onFailure();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    if (postListener != null)
                        postListener.onFailure();
                }
            });
        } else {
            Call<Boolean> call = iPostService.createPostImg(token, content, part);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (postListener != null)
                        if (response.body())
                            postListener.onSuccess();
                        else postListener.onFailure();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    if (postListener != null)
                        postListener.onFailure();
                }
            });
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

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (editPostListener != null)
                    if (response.body())
                        editPostListener.onSuccess();
                    else editPostListener.onFailure();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (editPostListener != null)
                    editPostListener.onFailure();
            }
        });
    }

    public void deleteClassPost(String postId) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.deletePost(token, postId);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (classListener != null)
                    if (response.body())
                        classListener.onDeleteSuccess();
                    else classListener.onDeleteFailure();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (classListener != null)
                    classListener.onDeleteFailure();
            }
        });

    }

    public void createPostComment(String content) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        String postId = SharedPreferencesUtils.loadClassPost(context).getId();
        Call<Boolean> call = iPostService.createComment(token, postId, content);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (commentListener != null)
                    if (response.body())
                        commentListener.onSuccess();
                    else commentListener.onFailure();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (commentListener != null)
                    commentListener.onFailure();
            }
        });

    }

    public void updatePostComment(String commentId, String content) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.updateComment(token, commentId, content);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (editCommentListener != null)
                    if (response.body())
                        editCommentListener.onSuccess();
                    else editCommentListener.onFailure();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (editCommentListener != null)
                    editCommentListener.onFailure();
            }
        });
    }

    public void deletePostComment(String commentId) {
        String token = SharedPreferencesUtils.loadToken(context).getTokenType() + " " + SharedPreferencesUtils.loadToken(context).getAccessToken();
        Call<Boolean> call = iPostService.deleteComment(token, commentId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (commentListener != null)
                    if (response.body())
                        commentListener.onSuccess();
                    else commentListener.onSuccess();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (commentListener != null)
                    commentListener.onFailure();
            }
        });
    }
}
