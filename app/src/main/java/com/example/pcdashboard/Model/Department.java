package com.example.pcdashboard.Model;

public class Department {
    private String id;
    private String title;
    private String time;
    private String content;
    private String image;

    public Department(String id, String title, String time, String content, String image) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
