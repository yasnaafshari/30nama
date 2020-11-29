package com.example.cinema.homePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.cinema.R;
import com.google.android.material.tabs.TabLayout;

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