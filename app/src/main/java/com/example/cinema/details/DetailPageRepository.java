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
        service.getTitlesDetails(url).enqueue(new Callback<TitlesDetailsModel>(){
            @Override
            public void onResponse(Call<TitlesDetailsModel> call, Response<TitlesDetailsModel> response) {
                TitlesDetailsModel titlesDetailsModel = response.body();
                detailsPageCallBack.onSuccess(titlesDetailsModel);
            }

            @Override
            public void onFailure(Call<TitlesDetailsModel> call, Throwable t) {

            }
        });
    }
    public interface DetailsPageCallBack{
        void onSuccess(TitlesDetailsModel titlesDetailsModel);
        void onFailure(String onFailureNote);
    }
}
