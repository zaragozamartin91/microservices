package com.example.tokenauth.user;

public class UserForm {
    private String email;
    private String password;

    public UserForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
