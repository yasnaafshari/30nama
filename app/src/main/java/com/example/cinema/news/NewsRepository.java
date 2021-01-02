package com.example.cinema.news;

import com.example.cinema.core.DataCallBack;
import com.example.cinema.core.Network;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    public void getNewsPage(DataCallBack<List<NewsModel>> dataCallBack) {
        NewsService service = Network.retrofit.create(NewsService.class);
        service.getNews().enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                List<NewsModel> newsModel = response.body();
                dataCallBack.onSuccess(newsModel);
            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                dataCallBack.onFailure(t.toString());
            }
        });
    }
}
