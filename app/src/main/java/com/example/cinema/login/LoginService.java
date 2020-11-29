package com.example.cinema.login;

import com.example.cinema.homePage.HomePageModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    @GET("captcha")
    Call<CaptchaModel> getCaptcha();
    @POST("login")
    Call<LoginResponseModel> login(@Body LoginInformation loginInformation);
}
