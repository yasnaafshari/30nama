package com.example.cinema.search;

import com.example.cinema.homePage.Title;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("search")
    Call<List<Title>> getSearchResult(@Query("term") String term);
}
