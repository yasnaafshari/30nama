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

import com.example.cinema.core.Network;
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

        HomePageRepository homePageRepository = new HomePageRepository(new HomePageRepository.HomePageCallBack() {
            @Override
            public void onSuccess(HomePageModel homePageModel) {
                titlesPager.setAdapter(new TitlesPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                        homePageModel));
            }

            @Override
            public void onFailure(String onFailureMessage) {

            }
        });

            homePageRepository.getHomePage();



    }
}