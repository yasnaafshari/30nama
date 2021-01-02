package com.example.cinema.news;

import com.example.cinema.details.TvShowsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NewsService {
    @GET("news")
    Call<List<NewsModel>> getNews();
}
