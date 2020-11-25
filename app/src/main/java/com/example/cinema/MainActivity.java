package com.example.cinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cinema.titleslist.TitlesListViewHolder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cinama.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HomePageService service = retrofit.create(HomePageService.class);
        service.getHomePage().enqueue(new Callback<HomePageModel>() {
            @Override
            public void onResponse(Call<HomePageModel> call, Response<HomePageModel> response) {
                response.body();
                HomePageModel homePageModel =response.body();
                RecyclerView recyclerView = findViewById(R.id.mainRecycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new MainListAdapter(homePageModel));
            }

            @Override
            public void onFailure(Call<HomePageModel> call, Throwable t) {

            }
        });


    }
}