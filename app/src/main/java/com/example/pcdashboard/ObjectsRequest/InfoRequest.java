package com.example.pcdashboard.ObjectsRequest;

public class InfoRequest {
    private String userId;
    private String email;
    private String phone;

    public InfoRequest(String userId, String email, String phone) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
