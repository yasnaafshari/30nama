package com.example.cinema.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinema.core.DataCallBack;

import java.util.List;

public class NewsViewModel extends ViewModel {
    private NewsRepository mNewsRepository = new NewsRepository();
    private MutableLiveData<List<NewsModel>> mNewsModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorNewsViewModelLiveData = new MutableLiveData<>();

    public LiveData<List<NewsModel>> getNews() {
        if (mNewsModelMutableLiveData.getValue() == null) {
            mNewsRepository.getNewsPage(new DataCallBack<List<NewsModel>>() {
                @Override
                public void onSuccess(List<NewsModel> newsModels) {
                    mNewsModelMutableLiveData.setValue(newsModels);
                }

                @Override
                public void onFailure(String onFailureNote) {
                    errorNewsViewModelLiveData.setValue(onFailureNote);
                }
            });
        }
        return mNewsModelMutableLiveData;
    }
}
