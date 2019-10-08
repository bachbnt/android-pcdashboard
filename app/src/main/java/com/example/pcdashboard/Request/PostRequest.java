package com.example.pcdashboard.Request;

public class PostRequest {
    private String content;
    private String image;

    public PostRequest(String content, String image) {
        this.content = content;
        this.image = image;
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
