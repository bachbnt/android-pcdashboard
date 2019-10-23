package com.example.pcdashboard.Model;

public class ChatMessage {
    private String content;
    private String time;
    private String userId;
    private String userName;
    private String userAvatar;

    public ChatMessage() {
    }

    public ChatMessage(String content, String time, String userId, String userName, String userAvatar) {
        this.content = content;
        this.time = time;
        this.userId = userId;
        this.userName = userName;
        this.userAvatar = userAvatar;
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
