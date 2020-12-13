package com.example.cinema.homePage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HomePageService {
    @GET("homePage")
    Call<HomePageModel> getHomePage(@Header("token") String token);
}
