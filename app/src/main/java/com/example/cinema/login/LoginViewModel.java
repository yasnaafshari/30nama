package com.example.cinema.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinema.core.DataCallBack;

public class LoginViewModel extends ViewModel {
    private LoginRepository yLoginRepository = new LoginRepository();

    public LiveData<String> login(LoginRequest loginRequest) {
         MutableLiveData<String> tokenLiveData = new MutableLiveData<>();
        yLoginRepository.login(loginRequest, new DataCallBack<String>() {
            @Override
            public void onSuccess(String token) {
                tokenLiveData.setValue(token);

            }

            @Override
            public void onFailure(String message) {
                System.out.println(message);
            }
        });
        return tokenLiveData;
    }


    public LiveData<CaptchaModel> getCaptcha() {
        MutableLiveData<CaptchaModel> captchaModelLiveData = new MutableLiveData<>();
        yLoginRepository.getCaptch(new DataCallBack<CaptchaModel>() {
            @Override
            public void onSuccess(CaptchaModel captchaModel) {
                captchaModelLiveData.setValue(captchaModel);
            }

            @Override
            public void onFailure(String message) {

            }
        });
        return captchaModelLiveData;
    }
}
