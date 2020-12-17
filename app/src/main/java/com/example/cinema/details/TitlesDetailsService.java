package com.example.cinema.details;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TitlesDetailsService {
    @GET("titlesDetails")
    Call<TvShowsModel.TitlesDetailsModel> getTitlesDetails(@Query("url") String url);
    @GET("titlesDetails")
    Call<TvShowsModel> getTvShows(@Query("url") String url, @Header("token") String token);
}
