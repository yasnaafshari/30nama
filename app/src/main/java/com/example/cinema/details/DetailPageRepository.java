package com.example.cinema.details;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinema.R;
import com.example.cinema.core.DataCallBack;
import com.example.cinema.core.Network;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPageRepository {

    public void getDetailedPage(String url, DataCallBack<TvShowsModel.TitlesDetailsModel> detailsModelDataCallBack) {
        TitlesDetailsService service = Network.retrofit.create(TitlesDetailsService.class);
        service.getTitlesDetails(url).enqueue(new Callback<TvShowsModel.TitlesDetailsModel>() {
            @Override
            public void onResponse(Call<TvShowsModel.TitlesDetailsModel> call, Response<TvShowsModel.TitlesDetailsModel> response) {
                TvShowsModel.TitlesDetailsModel titlesDetailsModel = response.body();
                detailsModelDataCallBack.onSuccess(titlesDetailsModel);
            }

            @Override
            public void onFailure(Call<TvShowsModel.TitlesDetailsModel> call, Throwable t) {
                detailsModelDataCallBack.onFailure("an unknown error has occurred");
            }
        });
    }


    public void getTvShows(String url, String token, DataCallBack<TvShowsModel> dataCallBack) {
        TitlesDetailsService service = Network.retrofit.create(TitlesDetailsService.class);
        service.getTvShows(url, token).enqueue(new Callback<TvShowsModel>() {
            @Override
            public void onResponse(Call<TvShowsModel> call, Response<TvShowsModel> response) {
                TvShowsModel tvShowsModel = response.body();
                dataCallBack.onSuccess(tvShowsModel);
            }

            @Override
            public void onFailure(Call<TvShowsModel> call, Throwable t) {
                dataCallBack.onFailure("an unknown error has occurred");
            }
        });


    }
}
