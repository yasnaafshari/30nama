package com.example.cinema.titleslist;

import com.example.cinema.homePage.Title;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TitleActionsService {

    @POST("watchList")
    Call<Object> addToWatchList(@Header("token") String token, @Query("id") int  id, @Query("nonce") String nonce);
    @POST("downloads")
    Call<Object> addToDownload(@Header("token") String token, @Query("id") int  id, @Query("nonce") String nonce );
    @POST("favourites")
    Call<Object> addToFavourite(@Header("token") String token, @Query("id") int  id, @Query("nonce") String nonce );
}
