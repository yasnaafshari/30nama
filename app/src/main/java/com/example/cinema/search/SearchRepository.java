package com.example.cinema.search;

import com.example.cinema.core.DataCallBack;
import com.example.cinema.core.Network;
import com.example.cinema.homePage.Title;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    public void getSearchResult(String term, DataCallBack<List<Title>> dataCallBack) {
        SearchService searchService = Network.retrofit.create(SearchService.class);
        searchService.getSearchResult(term).enqueue(new Callback<List<Title>>() {
            @Override
            public void onResponse(Call<List<Title>> call, Response<List<Title>> response) {
                List<Title> titles = response.body();
                dataCallBack.onSuccess(titles);
            }

            @Override
            public void onFailure(Call<List<Title>> call, Throwable t) {

            }
        });
    }


}
