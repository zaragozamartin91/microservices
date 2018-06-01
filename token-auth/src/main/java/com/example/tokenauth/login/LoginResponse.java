package com.example.tokenauth.login;

public class LoginResponse {
    private String msg;
    private String token;

    public LoginResponse(String msg, String token) {
        this.msg = msg;
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public String getToken() {
        return token;
    }
}
