package com.example.cinema.core;

import com.example.cinema.details.TitlesDetailsService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://cinama.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
