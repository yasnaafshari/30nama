package com.example.cinema.details;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TitlesDetailsService {
    @GET("titlesDetails")
    Call<TitlesDetailsModel> getTitlesDetails(@Query("url") String url);
}
