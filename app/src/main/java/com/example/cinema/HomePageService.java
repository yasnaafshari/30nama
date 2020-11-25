package com.example.cinema;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomePageService {
    @GET("homePage")
    Call<HomePageModel> getHomePage();
}
