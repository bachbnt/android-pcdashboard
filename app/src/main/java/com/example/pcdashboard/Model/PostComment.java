package com.example.pcdashboard.Model;

public class PostComment {
    private String id;
    private String content;
    private String time;
    private String postId;
    private String userId;
    private String userName;
    private String userAvatar;

    public PostComment(String id, String content, String time,String postId, String userId, String userName, String userAvatar) {
        this.id = id;
        this.content = content;
        this.time=time;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.userAvatar = userAvatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
