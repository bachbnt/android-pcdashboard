package com.example.pcdashboard.Model;

public class ClassPost {
    private String id;
    private String time;
    private String content;
    private String image;
    private String userId;
    private String userName;
    private String userAvatar;

    public ClassPost() {
    }

    public ClassPost(String id, String time, String content, String image, String userId, String userName, String userAvatar) {
        this.id = id;
        this.time = time;
        this.content = content;
        this.image = image;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
