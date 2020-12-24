package com.example.cinema.login;

import com.example.cinema.core.DataCallBack;
import com.example.cinema.core.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {


    LoginService service = Network.retrofit.create(LoginService.class);

    void login(LoginRequest loginRequest, DataCallBack<String> dataCallBack) {
        service.login(loginRequest).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                String token = response.body().data;
                dataCallBack.onSuccess(token);


            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

            }
        });
    }

    void getCaptch(DataCallBack<CaptchaModel> dataCallBack) {
        service.getCaptcha().enqueue(new Callback<CaptchaModel>() {
            @Override
            public void onResponse(Call<CaptchaModel> call, Response<CaptchaModel> response) {
                dataCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CaptchaModel> call, Throwable t) {

            }
        });
    }
}
