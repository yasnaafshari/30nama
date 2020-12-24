package com.example.cinema.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinema.core.DataCallBack;
import com.example.cinema.homePage.Title;

import java.util.List;

public class SearchViewModel extends ViewModel {
    SearchRepository searchRepository = new SearchRepository();
    MutableLiveData<List<Title>> searchLiveData = new MutableLiveData<>();

    public LiveData<List<Title>> getSearchResults(String s) {
        searchRepository.getSearchResult(s.toString(), new DataCallBack<List<Title>>() {
            @Override
            public void onSuccess(List<Title> titles) {
             searchLiveData.setValue(titles);
            }

            @Override
            public void onFailure(String onFailureNote) {

            }
        });
        return searchLiveData;
    }

}
