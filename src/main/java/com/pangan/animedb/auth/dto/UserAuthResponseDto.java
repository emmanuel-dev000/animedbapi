package com.pangan.animedb.auth.dto;

public class UserAuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}