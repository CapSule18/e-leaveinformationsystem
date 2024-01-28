package com.example.projectcuti.model;

public class UserCredentials {
    private String NIP;
    private String password;

    public UserCredentials(String NIP, String password) {
        this.NIP = NIP;
        this.password = password;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
