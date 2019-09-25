package com.example.pcdashboard.API;

import android.content.Context;

public class PostService {
    private static PostService postService;
    private static IPostService iPostService;
    private Context context;
    private PostListener listener;
    interface PostListener{
        void onSuccess();
        void onFailure();
    }

    public PostService(Context context, PostListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public static PostService getInstance(Context context,PostListener listener){
        if(postService==null)
            postService=new PostService(context,listener);
        return postService;
    }
}
