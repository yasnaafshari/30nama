package com.example.cinema.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinema.core.DataCallBack;

public class DetailsViewModel extends ViewModel {
    DetailPageRepository mDetailPageRepository = new DetailPageRepository();
    private MutableLiveData<TvShowsModel.TitlesDetailsModel> mTvShowsModelLiveData = new MutableLiveData<>();

    public LiveData<String> getOnError() {
        return onError;
    }

    private MutableLiveData<String> onError = new MutableLiveData<>();

    public LiveData<TvShowsModel.TitlesDetailsModel> fetchTitleDetails(String url) {
        mDetailPageRepository.getDetailedPage(url, new DataCallBack<TvShowsModel.TitlesDetailsModel>() {
            @Override
            public void onSuccess(TvShowsModel.TitlesDetailsModel titlesDetailsModel) {
                mTvShowsModelLiveData.setValue(titlesDetailsModel);
            }


            @Override
            public void onFailure(String onFailureNote) {
                onError.setValue(onFailureNote);
            }
        });
        return mTvShowsModelLiveData;
    }

    private MutableLiveData<TvShowsModel> mTvShowsModelMutableLiveData = new MutableLiveData<>();
    public LiveData<TvShowsModel> fetchTvShows(String token, String url) {
        mDetailPageRepository.getTvShows(url, token, new DataCallBack<TvShowsModel>() {
            @Override
            public void onSuccess(TvShowsModel tvShowsModel) {
                mTvShowsModelMutableLiveData.setValue(tvShowsModel);
            }


            @Override
            public void onFailure(String onFailureNote) {
                onError.setValue(onFailureNote);

            }
        });
        return mTvShowsModelMutableLiveData;
    }

}
