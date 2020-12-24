package com.example.cinema.homePage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinema.core.DataCallBack;

public class HomePageViewModel extends ViewModel {
    HomePageRepository homePageRepository = new HomePageRepository();
    private MutableLiveData<HomePageModel> mHomePageViewModelLiveData = new MutableLiveData<>();

    public LiveData<HomePageModel> getHomePage(String token) {
        if (mHomePageViewModelLiveData.getValue() == null)
        {homePageRepository.getHomePage(token, new DataCallBack<HomePageModel>() {
            @Override
            public void onSuccess(HomePageModel homePageModel) {
                mHomePageViewModelLiveData.setValue(homePageModel);
            }

            @Override
            public void onFailure(String onFailureMessage) {
                System.out.println(onFailureMessage);
            }
        });}
        return mHomePageViewModelLiveData;

    }

}
