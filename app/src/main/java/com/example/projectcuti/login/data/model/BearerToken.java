package com.example.projectcuti.login.data.model;

public class BearerToken {
    private String token;

    public BearerToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
