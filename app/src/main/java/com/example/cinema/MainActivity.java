package com.example.cinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.example.cinema.titleslist.TitlesListViewHolder;
import com.google.android.material.tabs.TabLayout;

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

        ViewPager titlesPager = findViewById(R.id.titlesPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(titlesPager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cinama.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HomePageService service = retrofit.create(HomePageService.class);
        service.getHomePage().enqueue(new Callback<HomePageModel>() {
            @Override
            public void onResponse(Call<HomePageModel> call, Response<HomePageModel> response) {
                HomePageModel homePageModel = response.body();
                titlesPager.setAdapter(new TitlesPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                        homePageModel));
            }

            @Override
            public void onFailure(Call<HomePageModel> call, Throwable t) {

            }
        });


    }
}