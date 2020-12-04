package com.example.cinema.homePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cinema.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private ViewPager yTitlesPager;
    private HomePageModel yHomePageModel;

    public HomeFragment() {
        HomePageRepository homePageRepository = new HomePageRepository(new HomePageRepository.HomePageCallBack() {
            @Override
            public void onSuccess(HomePageModel homePageModel) {
                yHomePageModel = homePageModel;
                yTitlesPager.setAdapter(new TitlesPagerAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                        homePageModel));
            }

            @Override
            public void onFailure(String onFailureMessage) {

            }
        });

        homePageRepository.getHomePage();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        yTitlesPager = getView().findViewById(R.id.titlesPager);
        TabLayout tabLayout = getView().findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(yTitlesPager);
        if (yHomePageModel != null) {
            yTitlesPager.setAdapter(new TitlesPagerAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                    yHomePageModel));
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
}
