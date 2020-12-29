package com.example.cinema.homePage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.cinema.core.DataCallBack;
import com.example.cinema.core.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageRepository {

    public void getHomePage(String token, DataCallBack<HomePageModel> dataCallBack) {
        HomePageService service = Network.retrofit.create(HomePageService.class);
        service.getHomePage(token).enqueue(new Callback<HomePageModel>() {
            @Override
            public void onResponse(Call<HomePageModel> call, Response<HomePageModel> response) {
              if(response.isSuccessful()) {
                  HomePageModel homePageModel = response.body();
                  dataCallBack.onSuccess(homePageModel);
              }
              else {
                  dataCallBack.onFailure("an unknown error has occurred");
              }

            }

            @Override
            public void onFailure(Call<HomePageModel> call, Throwable t) {
                dataCallBack.onFailure(t.getMessage());
            }
        });
    }


}
