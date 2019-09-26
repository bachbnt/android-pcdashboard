package com.example.pcdashboard.Model;

public class Token {
    private String accessToken;
    private String tokenType;
    private String userId;

    public Token(String accessToken, String tokenType, String userId) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
