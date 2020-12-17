package com.example.cinema.details;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinema.R;
import com.example.cinema.core.Network;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPageRepository {

    public void getDetailedPage(String url, DetailsPageCallBack detailsPageCallBack){
        TitlesDetailsService service = Network.retrofit.create(TitlesDetailsService.class);
        service.getTitlesDetails(url).enqueue(new Callback<TvShowsModel.TitlesDetailsModel>(){
            @Override
            public void onResponse(Call<TvShowsModel.TitlesDetailsModel> call, Response<TvShowsModel.TitlesDetailsModel> response) {
                TvShowsModel.TitlesDetailsModel titlesDetailsModel = response.body();
                detailsPageCallBack.onSuccess(titlesDetailsModel);
            }

            @Override
            public void onFailure(Call<TvShowsModel.TitlesDetailsModel> call, Throwable t) {

            }
        });
    }
    public interface DetailsPageCallBack{
        void onSuccess(TvShowsModel.TitlesDetailsModel titlesDetailsModel);
        void onFailure(String onFailureNote);
    }
    public void getTvShows(String url,String token, TvShowsCallBack tvShowsCallBack ){
        TitlesDetailsService service = Network.retrofit.create(TitlesDetailsService.class);
        service.getTvShows(url, token).enqueue(new Callback<TvShowsModel>() {
            @Override
            public void onResponse(Call<TvShowsModel> call, Response<TvShowsModel> response) {
                TvShowsModel tvShowsModel = response.body();
                tvShowsCallBack.onSuccess(tvShowsModel);
            }

            @Override
            public void onFailure(Call<TvShowsModel> call, Throwable t) {

            }
        });


        }
    public interface TvShowsCallBack{
        void onSuccess(TvShowsModel tvShowsModel);
        void onFailure(String onFailureNote);
    }
}
