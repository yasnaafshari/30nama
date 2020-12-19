package com.example.cinema.core;

import com.example.cinema.details.TvShowsModel;

public interface DataCallBack<T>{
        void onSuccess(T data);
        void onFailure(String onFailureNote);
    }

