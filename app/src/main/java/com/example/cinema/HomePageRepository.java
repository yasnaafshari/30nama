package com.example.cinema;

import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cinema.core.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageRepository {
    HomePageCallBack yHomePageCallBack;

    public HomePageRepository(HomePageCallBack homePageCallBack) {
        yHomePageCallBack = homePageCallBack;
    }

    public void getHomePage() {
        HomePageService service = Network.retrofit.create(HomePageService.class);
        service.getHomePage().enqueue(new Callback<HomePageModel>() {
            @Override
            public void onResponse(Call<HomePageModel> call, Response<HomePageModel> response) {
                HomePageModel homePageModel = response.body();
                yHomePageCallBack.onSuccess(homePageModel);

            }

            @Override
            public void onFailure(Call<HomePageModel> call, Throwable t) {
                yHomePageCallBack.onFailure(t.getMessage());
            }
        });
    }


    public interface HomePageCallBack {
        void onSuccess(HomePageModel homePageModel);

        void onFailure(String onFailureMessage);
    }
}
