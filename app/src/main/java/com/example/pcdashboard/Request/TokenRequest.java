package com.example.pcdashboard.Request;

public class TokenRequest {
    public String userId;
    public String password;

    public TokenRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
