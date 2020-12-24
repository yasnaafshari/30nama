package com.example.cinema.login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("user_login")
    String email;
    @SerializedName("pass")
    String password;
    String captcha;
    String session;
    public LoginRequest(String email, String password, String captcha, String session) {
        this.email = email;
        this.password = password;
        this.captcha = captcha;
        this.session = session;
    }

}
