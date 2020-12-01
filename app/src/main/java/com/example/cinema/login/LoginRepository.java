package com.example.cinema.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import com.example.cinema.R;
import com.example.cinema.core.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    public interface LoginCallback {
        void onSuccess(String token);

        void onFailure(String message);


    }

    LoginService service = Network.retrofit.create(LoginService.class);

    void login(LoginInformation loginInformation, LoginCallback loginCallback) {
        service.login(loginInformation).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                String token = response.body().data;
                loginCallback.onSuccess(token);


            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

            }
        });
    }
public interface CaptchaCallback{
        void onSuccess(CaptchaModel captchaModel);
        void onFailure(String message);
}
    void getCaptch(CaptchaCallback captchaCallback) {
        service.getCaptcha().enqueue(new Callback<CaptchaModel>() {
            @Override
            public void onResponse(Call<CaptchaModel> call, Response<CaptchaModel> response) {
                captchaCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CaptchaModel> call, Throwable t) {

            }
        });
    }
}
