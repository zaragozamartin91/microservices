package com.example.tokenauth.user;

public class UserForm {
    private String name;
    private String email;

    public UserForm(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
