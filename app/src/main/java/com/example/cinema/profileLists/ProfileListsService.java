package com.example.cinema.profileLists;

import com.example.cinema.homePage.Title;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ProfileListsService {
    @GET("watchList")
    Call<List<Title>> getWatchList(@Header("token") String token);
    @GET("downloads")
    Call<List<Title>> getDownload(@Header("token") String token);
    @GET("favourites")
    Call<List<Title>> getFavourite(@Header("token") String token);
}
