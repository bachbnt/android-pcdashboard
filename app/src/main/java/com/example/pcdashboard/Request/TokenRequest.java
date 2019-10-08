package com.example.pcdashboard.Request;

public class TokenRequest {
    private String userId;
    private String password;

    public TokenRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
