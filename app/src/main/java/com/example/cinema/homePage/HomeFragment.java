package com.example.cinema.homePage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.cinema.R;
import com.example.cinema.core.BaseFragment;
import com.example.cinema.core.DataCallBack;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends BaseFragment {

    private ViewPager yTitlesPager;
    private HomePageModel yHomePageModel;
    private HomePageViewModel mHomePageViewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHomePageViewModel = new ViewModelProvider(getActivity()).get(HomePageViewModel.class);


        yTitlesPager = getView().findViewById(R.id.titlesPager);

        TabLayout tabLayout = getView().findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(yTitlesPager);
        if (yHomePageModel != null) {
            yTitlesPager.setAdapter(new TitlesPagerAdapter(getParentFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                    yHomePageModel));
        }

        setUpHomePage();
        mHomePageViewModel.getErrorHomePageViewModelLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showError(s);
            }
        });

    }

    private void setUpHomePage() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String token = preferences.getString("token", null);
        mHomePageViewModel.getHomePage(token).observe(getViewLifecycleOwner(), new Observer<HomePageModel>() {

            @Override
            public void onChanged(HomePageModel homePageModel) {
                yHomePageModel = homePageModel;
                yTitlesPager.setAdapter(new TitlesPagerAdapter(getParentFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                        homePageModel));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onRetryClicked() {
        setUpHomePage();
    }
}
