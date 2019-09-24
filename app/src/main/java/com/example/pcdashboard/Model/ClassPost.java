package com.example.pcdashboard.Model;

public class ClassPost {
    private String id;
    private String avatar;
    private String name;
    private String time;
    private String content;
    private String image;

    public ClassPost(String id, String avatar, String name, String time, String content, String image) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.time = time;
        this.content = content;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
