package com.OnlineGame.backend.config.dto;

import lombok.Builder;

@Builder
public class AuthenticationResponse {
    private String token;
    private UserResponse user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}