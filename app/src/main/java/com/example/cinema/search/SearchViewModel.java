package com.example.cinema.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinema.core.DataCallBack;
import com.example.cinema.homePage.Title;

import java.util.List;

public class SearchViewModel extends ViewModel {
    SearchRepository searchRepository = new SearchRepository();
    private MutableLiveData<List<Title>> searchLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorMutableLiveData = new MutableLiveData<>();

    public LiveData<String> getErrorMutableLiveData() {
        return errorMutableLiveData;
    }

    public LiveData<List<Title>> getSearchResults(String s) {
        searchRepository.getSearchResult(s.toString(), new DataCallBack<List<Title>>() {
            @Override
            public void onSuccess(List<Title> titles) {
                searchLiveData.setValue(titles);
            }

            @Override
            public void onFailure(String onFailureNote) {
                errorMutableLiveData.setValue(onFailureNote);
            }
        });
        return searchLiveData;
    }

}
