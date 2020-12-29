package com.example.cinema.profile;

import com.example.cinema.core.DataCallBack;
import com.example.cinema.core.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    public void getProfile(String token, DataCallBack<ProfileModel> dataCallBack) {
        ProfileService profileService = Network.retrofit.create(ProfileService.class);
        profileService.getProfile(new TokenModel(token)).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                ProfileModel profileModel = response.body();
                dataCallBack.onSuccess(profileModel);
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
            }
        });

    }

}
